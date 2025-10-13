document.addEventListener("DOMContentLoaded", initApp);





async function initApp(){
    console.log("js loaded");

    bookings = document.querySelector("#bookingForm").addEventListener("submit", fetchBookings);

    
    console.log("test");
    



}

function seeBookings(bookings){
    bookings.forEach(booking => {
        renderBooking(booking)
    });
}

function renderBooking(booking) {
  const row = document.createElement("tr");

  row.appendChild(renderBookingElement(booking.customerEmail));
  row.appendChild(renderBookingElement(booking.customerPhoneNumber));


  
  const seats = booking.tickets.map(ticket => {
    if (ticket.seat) {
      return `Række ${ticket.seat.row}, Sæde ${ticket.seat.seatNumber}`;
    } else {
      return "Ukendt sæde";
    }
  }).join("<br>");

  const tdDelete = document.createElement("td");
  const deleteBtn = document.createElement("button");
  deleteBtn.textContent= "slet booking";
  deleteBtn.addEventListener("click", async () => {
    await fetch(`http://localhost:8080/api/orders/${booking.id}`,{
        method: "DELETE"
    });
    const bookingsTable = document.querySelector("#bookings");
        while (bookingsTable.rows.length > 1) {
            bookingsTable.deleteRow(1);
        }
  });
  deleteBtn.className = "delete-btn";
  deleteBtn.id = "deleteBtn";
  tdDelete.appendChild(deleteBtn);

  row.appendChild(renderBookingElement(seats));
  row.appendChild(tdDelete)


  document.querySelector("#bookings").appendChild(row);
}

function renderBookingElement(value) {
  const cell = document.createElement("td");
  cell.innerHTML = value;
  return cell;
}

async function fetchBookings(e){
    e.preventDefault();
    const form = document.querySelector("#bookingForm");

    const phone = document.getElementById("phone").value;
    const email = document.getElementById("email").value;

    let url = "http://localhost:8080/api/orders"


    if (email){
        url += `?customerEmail=${encodeURIComponent(email)}`;
    } else if (phone) {
        url += `?customerPhoneNumbe=${phone}`;
    } else {
        alert("Indtast email eller telefon nummer");
        return;
    }

    const resp = await fetch(url);
    if (!resp.ok){
        console.error("Error loading orders");
        return;
    }

    const data = await resp.json();
    console.log("form submitted");

    

    const bookingsTable = document.querySelector("#bookings");
        while (bookingsTable.rows.length > 1) {
            bookingsTable.deleteRow(1);
        }

    
    seeBookings(data); 
    form.reset();

    return data;
}