document.addEventListener("DOMContentLoaded", initApp);


const movieUrl = "http://localhost:8080/api/shows/movie/";
const movieId = new URLSearchParams(window.location.search).get("movieId"); // get the movieId for the currently showing movie


async function initApp(){
    console.log(movieUrl+movieId);

    shows = await fetchShows();


    renderShows(shows);

    document.querySelector("#createShowForm").addEventListener("submit", createShow);


}

async function createShow(e){
    e.preventDefault();
    
    console.log("clicked");

    const form= e.target;
    const show= {
        hall: { id: parseInt(form.hall.value) },
        timeOfShowing: form.time.value,
        movie: { id: parseInt(movieId) } 
    }

    const resp = await fetch("http://localhost:8080/api/shows",{
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(show)
    });

    if (!resp.ok){
        console.error("Error creating show");
    }

    const data = await resp.json();

    form.reset();
    refreshPage();
    return data;
}

async function fetchShows(){
    const resp = await fetch(movieUrl+movieId);
    if (!resp.ok){
        console.log("Error: "+resp.status);
    }
    return await resp.json();
}

function renderShows(shows) {
    const tableBody = document.querySelector("#showsTableBody");
    tableBody.innerHTML = ""; 
    
    shows.forEach(show => {
        if (show.cancelled === false) {
            renderShow(show);
        }
    });
}

function renderShow(show) {
    const row = document.createElement("tr");

    row.appendChild(renderCell(show.id));

    const showTime = new Date(show.timeOfShowing).toLocaleString();
    row.appendChild(renderCell(showTime));

    row.appendChild(renderCell(show.hall.name ?? ("Hall #" + show.hall.id)));

    const availableSeats = show.hall.seats ? show.hall.seats.length - show.tickets.length : "N/A";
    row.appendChild(renderCell(availableSeats));

    const bookButtonCell = document.createElement("td");
    const bookButton = document.createElement("button");
    bookButton.textContent = "Book Tickets";
    bookButton.classList.add("bookButton");
    bookButton.addEventListener("click", () => handleBookClick(show));

    const deleteButtonCell = document.createElement("td");
    const deleteButton = document.createElement("button");
    deleteButton.textContent= "Delete Show";
    deleteButton.classList.add("deleteButton");
    deleteButton.addEventListener("click", async () => {
        await fetch(`http://localhost:8080/api/shows/${show.id}`,{
            method: "DELETE"
        });
        refreshPage();
    });
    
    
    bookButtonCell.appendChild(bookButton);
    row.appendChild(bookButtonCell);

    deleteButtonCell.appendChild(deleteButton);
    row.appendChild(deleteButtonCell)

    document.querySelector("#showsTable").appendChild(row);
}

function refreshPage(){
    window.location.reload();
} 

function renderCell(content) {
    const cell = document.createElement("td");
    cell.textContent = content;
    return cell;
}

function handleBookClick(show) {
    console.log("Booking show ID:", show.id);
    window.location.href = `booking.html?showId=${show.id}`;
}