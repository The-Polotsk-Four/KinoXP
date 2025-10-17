document.addEventListener("DOMContentLoaded", initApp);

const BASE_MOVIE_URL = "http://localhost:8080/api/movies"

async function initApp(){
    console.log("Hello world");

    movies = await fetchMovies();
    console.log(movies);

    renderMovies(movies);


}

async function fetchMovies(){

const resp = await fetch(BASE_MOVIE_URL);
if (!resp.ok){
    console.log("Error: " + resp.status);
}
return await resp.json();
}


function renderMovies(movies){
    movies.forEach(movie => {
        renderMovie(movie)
    });
}

function renderMovieElement(value) {
    const cell = document.createElement("td");
    cell.textContent = value;
    return cell;
}

function renderPosterCell(url) {
    const cell = document.createElement("td");
    const img = document.createElement("img");
    img.src = url;
    img.alt = "Poster";
    img.width = 80;
    cell.appendChild(img);
    return cell;
}


function renderMovie (movie){
    const row = document.createElement("tr");


    row.appendChild(renderMovieElement(movie.id));
    row.appendChild(renderMovieElement(movie.title));
    row.appendChild(renderMovieElement(movie.year));
    row.appendChild(renderPosterCell(movie.poster));

    const seeShowingCell= document.createElement("td");
    seeShowingCell.classList.add("movieShowing");
    const seeShowing=document.createElement("button");
    const movieId= "movie"+movie.id + "seeShowing";
    seeShowing.id= "id"+movieId;
    seeShowing.addEventListener("click", () => {
    window.location.href = `employeeShows.html?movieId=${movie.id}`;
});
    seeShowing.textContent="See showings";
    seeShowingCell.appendChild(seeShowing);
    row.appendChild(seeShowingCell);

    document.querySelector("#movieTable").append(row);



}