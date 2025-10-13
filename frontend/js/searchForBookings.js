document.addEventListener("DOMContentLoaded", initApp);





async function initApp(){
    console.log("js loaded");

    bookings = document.querySelector("#bookingForm").addEventListener("submit", fetchBookings);

    seeBookings(bookings); 
    console.log("test");
    



}

function seeBookings(bookings){
    bookings.forEach(booking => {
        renderBooking(booking)
    });
}

function renderBooking(booking){

    console.log(booking);
    


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
    

    form.reset();

    return data;
}