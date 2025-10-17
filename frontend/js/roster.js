document.addEventListener("DOMContentLoaded", initApp);

const rosterUrl = 'http://localhost:8080/api/roster';
const userUrl = 'http://localhost:8080/api/users';
const calender = document.querySelector("#date");
const editPopup = document.querySelector("#roster-popup")
let date = new Date;
let roster;
let users;

async function initApp() {
    // console.log('date: ' + date);
    calender.addEventListener('input', getDate);
    editPopup.addEventListener('submit', handleEditSubmit);
    // console.log(rosterUrl);
    await fetchRoster();
    await fetchUsers();
    console.log('roster: ' + roster);
    console.log('users: ' + users);
    renderRoster(roster);
}

async function fetchRoster() {
    const formattedDate = date.toISOString().split('T')[0];
    // console.log('date in fetch: ' + formattedDate);
    const dateUrl = `?date=${formattedDate}`;
    console.log('dateUrl: ' + rosterUrl + dateUrl);
    const response = await fetch(rosterUrl + dateUrl);
    if (!response.ok) {
        console.log("Error: " + response.status);
        document.querySelector("#messages").textContent = "FEJL!";
        document.querySelector("#messages").classList.toggle("error");
    }
    // console.log('roster fetched');
    roster = await response.json();
    await createTimeSlotsForDate(formattedDate);
    return roster;
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
        button.addEventListener("click",  handleEditClick);
    } else if (content === 'delete') {
        button.appendChild(document.createTextNode('slet vagt'));
        button.addEventListener("click",  handleDeleteClick);
    }
    button.id = `btn-${id}`;
    cell.appendChild(button);
    return cell;
}

function renderUser(user) {
    console.log(user);
    if (!user) {
        return 'NaN';
    }
    return user.name;
}

async function handleEditClick(event) {
    console.log('edit');
    console.log(event.target);
    const id = event.target.id.split('-')[1];
    toggleRosterPopup(event.target.id);
    let user = users.find(function (user) {
        return user.id === id;
    });
    console.log('user: ' + user.name);
}

async function handleEditSubmit(event) {
    event.preventDefault();
    console.log(event);
}

function toggleRosterPopup(id) {
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

        if (!res.ok) throw new Error("Failed to save day");
        // alert("✅ Day saved successfully!");
    } catch (err) {
        alert("❌ " + err.message);
    }
}

async function getDate() {
    const dateControl = document.querySelector('input[type="date"]');

    date.setTime(dateControl.valueAsNumber);

    await fetchRoster();
    renderRoster(roster);
}

async function createTimeSlotsForDate(formattedDate) {
    if (!roster.length > 0) {
        console.log('roster is not-populated');
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
        // console.log(fullWorkday);
        try {
            const res = await fetch('http://localhost:8080/api/roster', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(fullWorkday),
            });

            roster = fullWorkday;

            if (!res.ok) throw new Error("Failed to save day");
            // alert("✅ Day saved successfully!");
        } catch (err) {
            alert("❌ " + err.message);
        }
    }
}

