document.addEventListener("DOMContentLoaded", initApp);


const movieUrl = "http://localhost:8080/api/shows/movie/";
const movieId = new URLSearchParams(window.location.search).get("movieId"); // get the movieId for the currently showing movie
let getShows= [];


async function initApp(){
    console.log(movieUrl+movieId);

    shows = await fetchShows(); 
    console.log(shows);
    

    renderShows(shows);


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
    bookButtonCell.appendChild(bookButton);
    row.appendChild(bookButtonCell);

    document.querySelector("#showsTable").appendChild(row);
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