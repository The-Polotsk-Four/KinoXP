document.addEventListener("DOMContentLoaded", initApp);

const showUrl = "http://localhost:8080/api/shows/";
const showId = new URLSearchParams(window.location.search).get("showId");


async function initApp(){
    console.log("loaded");
    console.log("show id: "+showId);
    console.log(showUrl);
    

    bookings = await fetchBookings();

    console.log(bookings);
    console.log(bookings.hall.id);

    renderSeats(bookings);
}








function renderSeats(bookings) {
    const table = document.getElementById("seat-table");
    // table.innerHTML = "";

    const seats = bookings.hall.seats;

    const maxRow = Math.max(...seats.map(s => s.row));
    const maxCol = Math.max(...seats.map(s => s.seatNumber));

    for (let r = 1; r <= maxRow; r++) {
        const rowEl = document.createElement("tr");
        const rowSeats = seats.filter(s => s.row === r);

        const labelCell = document.createElement("td");
        labelCell.textContent = `${r}`;
        labelCell.style.fontWeight = "bold"; 
        rowEl.appendChild(labelCell);

        for (let c = 1; c <= maxCol; c++) {
            const seat = rowSeats.find(s => s.seatNumber === c);
            const cell = document.createElement("td");

            if (seat) {
                cell.textContent = seat.seatNumber; 
                cell.dataset.seatId = seat.id;
            }
            rowEl.appendChild(cell);
        }

        table.appendChild(rowEl);
    }
}





async function fetchBookings(){
    const resp = await fetch(showUrl+showId);
    if (!resp.ok){
        console.log("Error: "+resp.status);
    }
    return await resp.json();
}

