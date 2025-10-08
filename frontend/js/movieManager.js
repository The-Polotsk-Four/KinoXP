const searchForm = document.getElementById('searchForm');
const movieContainer = document.getElementById('movieContainer');
const saveButton = document.getElementById('saveButton');

let currentMovie = null;

// --- SEARCH MOVIE ---
searchForm.addEventListener('submit', async (e) => {
    e.preventDefault();

    const title = document.getElementById('title').value.trim();
    const year = document.getElementById('year').value.trim();

    if (!title) {
        alert("Please enter a movie title");
        return;
    }

    try {
        const url = `http://localhost:8080/api/movies/search/details?title=${encodeURIComponent(title)}&year=${encodeURIComponent(year)}`;
        const res = await fetch(url);

        if (!res.ok) throw new Error("Movie not found");
        const movie = await res.json();

        // Add dummy values to match MovieDto
        currentMovie = {
            ...movie,
            id: null,             // Backend will assign ID
            status: "COMING_SOON",    // Default enum value
            show: []              // Empty list
        };

        // Fill data in UI
        document.getElementById('poster').src = movie.poster;
        document.getElementById('movieTitle').textContent = movie.title;
        document.getElementById('movieYear').textContent = movie.year;
        document.getElementById('runtime').textContent = movie.runtime;
        document.getElementById('actors').textContent = movie.actors;
        document.getElementById('trailerLink').href = movie.trailer || "#";

        movieContainer.classList.remove('hidden');

    } catch (err) {
        alert("❌ " + err.message);
        movieContainer.classList.add('hidden');
    }
});

// --- SAVE MOVIE ---
saveButton.addEventListener('click', async () => {
    if (!currentMovie) {
        alert("No movie loaded!");
        return;
    }

    try {
        const res = await fetch('http://localhost:8080/api/movies', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(currentMovie)
        });


        if (!res.ok) throw new Error("Failed to save movie");
        alert("✅ Movie saved successfully!");
    } catch (err) {
        alert("❌ " + err.message);
    }
});
