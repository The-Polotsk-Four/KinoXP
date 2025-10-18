document.addEventListener("DOMContentLoaded", initApp);

const rosterUrl = 'http://localhost:8080/api/roster';
const userUrl = 'http://localhost:8080/api/users';
const calender = document.querySelector("#date");
const editPopup = document.querySelector("#roster-popup")
let date = new Date;
let roster;
let users;
let workingId;

async function initApp() {
    calender.addEventListener('input', getDate);
    editPopup.addEventListener('submit', handleEditSubmit);
    await makeDateChosenDate();
    await fetchRoster();
    await fetchUsers();
    console.log('roster: ' + roster);
    console.log('users: ' + users);
    renderRoster();
}

async function makeDateChosenDate() {
    document.querySelector("#date-title").innerHTML = date.toISOString().split('T')[0];
}

async function fetchRoster() {
    const formattedDate = date.toISOString().split('T')[0];
    const dateUrl = `?date=${formattedDate}`;
    console.log('dateUrl: ' + rosterUrl + dateUrl);
    await fetchRosterAndMakeJson(dateUrl);
    if (!roster.length > 0) {
        await createTimeSlotsForDate(formattedDate);
        await fetchRosterAndMakeJson(dateUrl);
    }
    console.log('loaded roster:');
    console.log(roster);
}

async function fetchRosterAndMakeJson(dateUrl) {
    const response = await fetch(rosterUrl + dateUrl);

    if (!response.ok) {
        console.log("Error: " + response.status);
        document.querySelector("#messages").textContent = "FEJL!";
        document.querySelector("#messages").classList.toggle("error");
    }

    roster = await response.json();
}

async function fetchUsers() {
    const response = await fetch(userUrl);
    if (!response.ok) {
        console.log("Error: " + response.status);
        document.querySelector("#messages").textContent = "FEJL!";
        document.querySelector("#messages").classList.toggle("error");
    }
    // console.log('roster fetched');
    users = await response.json();
    return users;

}

function renderRoster() {
    const tableBody = document.querySelector('#rosterTableBody');
    // console.log('tableBody innerHTML: ' + tableBody.innerHTML);
    tableBody.innerHTML = '';

    roster.forEach(roster => renderTimeSlot(roster));
}

function renderTimeSlot(timeSlot) {
    const row = document.createElement('tr');

    // row.appendChild(renderCell(timeSlot.id));
    row.appendChild(renderCell(timeSlot.date));
    row.appendChild(renderCell(timeSlot.startTime));
    row.appendChild(renderCell(timeSlot.endTime));
    row.appendChild(renderCell(timeSlot.role));
    row.appendChild(renderCell(renderUser(timeSlot.user)));

    row.appendChild(renderButtonCell('edit', timeSlot.id));

    row.appendChild(renderButtonCell('delete', timeSlot.id));

    document.querySelector('#rosterTableBody').appendChild(row);
}

function renderCell(content) {
    const cell = document.createElement('td');
    cell.textContent = content;
    return cell;
}

function renderButtonCell(content, id) {
    const cell = document.createElement('td');
    const button = document.createElement("button");
    if (content === 'edit') {
        button.appendChild(document.createTextNode('rediger vagt'));
        button.addEventListener("click", handleEditClick);
    } else if (content === 'delete') {
        button.appendChild(document.createTextNode('slet vagt'));
        button.addEventListener("click", handleDeleteClick);
    }
    button.id = `btn-${id}`;
    cell.appendChild(button);
    return cell;
}

function renderUser(user) {
    // console.log(user);
    if (!user) {
        return 'NaN';
    }
    return user.name;
}

async function handleEditClick(event) {
    // console.log('edit');
    // console.log(event.target);
    workingId = event.target.id.split('-')[1];
    toggleRosterPopup();
}

async function handleEditSubmit(event) {
    event.preventDefault();
    const name = event.target[0].value;
    let user = users.find(function (user) {
        return user.name.toLocaleLowerCase() === name.toLowerCase();
    });

    const timeSlotToUpdate = updateUserInRoster(user);
    console.log(timeSlotToUpdate);
    try {
        const res = await fetch(`http://localhost:8080/api/roster/${workingId}`, {
            method: 'PUT',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(timeSlotToUpdate),
        });

        if (!res.ok) {
            throw new Error("Something went wrong")
        } else {
            await fetchRoster();
            console.log('after edit fetch');
            console.log(roster);
            await renderRoster();
            toggleRosterPopup();
        }
        // alert("✅ Day saved successfully!");
    } catch (err) {
        alert("❌ " + err.message);
    }
    event.target[0].value = '';
}

function updateUserInRoster(user) {
    console.log(user);
    console.log('working id: ' + workingId);
    const timeSlotToUpdate = roster.filter(timeSlot => Number(timeSlot.id) === Number(workingId))[0];
    timeSlotToUpdate.user = user;
    console.log(timeSlotToUpdate);
    return timeSlotToUpdate;
}

function toggleRosterPopup() {
    const overlay = document.getElementById('roster-popup');
    overlay.classList.toggle('show');
}

async function handleDeleteClick(event) {
    console.log('delete');
    console.log(event.target);
    const id = event.target.id.split('-')[1];
    console.log(id);
    try {
        const res = await fetch(`http://localhost:8080/api/roster/${id}`, {
            method: 'DELETE',
        });

        roster = fullWorkday;

        if (!res.ok) throw new Error("Failed to delete");
        // alert("✅ Day saved successfully!");
    } catch (err) {
        alert("❌ " + err.message);
    }
}

async function getDate() {
    const dateControl = document.querySelector('input[type="date"]');

    date.setTime(dateControl.valueAsNumber);
    dateControl.value = '';

    await fetchRoster();
    await makeDateChosenDate();
    renderRoster();
}

async function createTimeSlotsForDate(formattedDate) {

    console.log('roster is not populated');
    const fullWorkday = [
        {
            "date": `${formattedDate}`,
            "startTime": "12:00:00",
            "endTime": "19:59:00",
            "role": "SALE"
        },
        {
            "date": `${formattedDate}`,
            "startTime": "20:00:00",
            "endTime": "23:59:00",
            "role": "SALE"
        },
        {
            "date": `${formattedDate}`,
            "startTime": "12:00:00",
            "endTime": "16:59:00",
            "role": "SALE"
        },
        {
            "date": `${formattedDate}`,
            "startTime": "17:00:00",
            "endTime": "20:59:00",
            "role": "SALE"
        },
        {
            "date": `${formattedDate}`,
            "startTime": "21:00:00",
            "endTime": "23:59:00",
            "role": "SALE"
        },
        {
            "date": `${formattedDate}`,
            "startTime": "12:00:00",
            "endTime": "17:59:00",
            "role": "OPERATOR"
        },
        {
            "date": `${formattedDate}`,
            "startTime": "18:00:00",
            "endTime": "23:59:00",
            "role": "OPERATOR"
        },
        {
            "date": `${formattedDate}`,
            "startTime": "12:00:00",
            "endTime": "17:59:00",
            "role": "FLOOR"
        },
        {
            "date": `${formattedDate}`,
            "startTime": "18:00:00",
            "endTime": "23:59:00",
            "role": "FLOOR"
        }
    ]

    try {
        const res = await fetch('http://localhost:8080/api/roster', {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(fullWorkday),
        });

        if (!res.ok) throw new Error("Failed to save day");
        // alert("✅ Day saved successfully!");
    } catch (err) {
        alert("❌ " + err.message);
    }
}

