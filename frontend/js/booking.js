document.addEventListener("DOMContentLoaded", initApp);

const showUrl = "http://localhost:8080/api/shows/";
const orderUrl = "http://localhost:8080/api/orders";
const showId = new URLSearchParams(window.location.search).get("showId");
const selectedSeats = new Set();


async function initApp(){
    console.log("loaded");
    console.log("show id: "+showId);
    console.log(showUrl);
    

    bookings = await fetchBookings();
    document.querySelector("#orderForm").addEventListener("submit", createBooking)


    console.log(bookings);
    console.log(bookings.hall.id);

    renderSeats(bookings);
}

async function createBooking(e){
    e.preventDefault();
    console.log("clicked");
    const form = document.querySelector("#orderForm");

    const newOrder = {
        customerPhoneNumber: form.phone.value,
        customerEmail: form.email.value,
        amountOfSeats: Array.from(selectedSeats)
    };


    const seatIdsParam = Array.from(selectedSeats).map(id => `seatId=${id}`).join("&");

    const resp = await fetch(`${orderUrl}?showId=${showId}&${seatIdsParam}`, {
    method: "POST",
    headers: {
        "Content-Type": "application/json"
    },
    body: JSON.stringify(newOrder)
});
    if (!resp.ok){
        console.error("Error in creating order");
        return;
    }
    const data = await resp.json();
    console.log("Order created: "+data);

    form.reset();

    selectedSeats.clear();

    return data;
}



function renderSeats(bookings) {
    const table = document.getElementById("seat-table");

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

                cell.addEventListener("click", () => {
                    if (selectedSeats.has(seat.id)) {
                        selectedSeats.delete(seat.id);
                        cell.classList.remove("selected");
                    } else {
                        selectedSeats.add(seat.id);
                        cell.classList.add("selected");
                    }
                    console.log("Selected seats:", Array.from(selectedSeats));
                });
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

