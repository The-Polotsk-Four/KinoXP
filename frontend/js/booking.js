document.addEventListener("DOMContentLoaded", initApp);

const showUrl = "http://localhost:8080/api/shows/";
const showId = new URLSearchParams(window.location.search).get("showId");
let seatArr=[];

async function initApp(){
    console.log("loaded");
    console.log("show id: "+showId);
    console.log(showUrl);
    

    bookings = await fetchBookings();
    let seatArr = bookings.seat.push;
    console.log(bookings.seat.id.length);
    

    console.log(bookings);
    console.log(bookings.hall.id);

    renderSeats(bookings);
}



function renderSeatId(value, seat){
    const cell = document.createElement("td");
    cell.textContent= value===seat;
    return cell
}




function renderSeats(bookings){
    
    if (bookings.hall.id===1){
        console.log("hall 1");
        for(let i=1; i<13; i++){
            const row = document.createElement("tr"+i);
            for (let x=1; x<21; x++){
                row.appendChild(renderSeatId(bookings.seat.id));
            }
        }

    } else{
        console.log("hall 2");
    }

}


async function fetchBookings(){
    const resp = await fetch(showUrl+showId);
    if (!resp.ok){
        console.log("Error: "+resp.status);
    }
    return await resp.json();
}

