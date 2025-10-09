document.addEventListener("DOMContentLoaded", initApp);

const rosterUrl = 'http://localhost:8080/api/roster';
const calender = document.querySelector("#date");
let date = new Date;
let roster;

async function initApp() {
    // console.log('date: ' + date);
    calender.addEventListener('input', getDate);
    // console.log(rosterUrl);
    roster = await fetchRoster();
    console.log('roster: ' + roster);
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

function renderRoster() {
    const tableBody = document.querySelector('#rosterTableBody');
    // console.log('tableBody innerHTML: ' + tableBody.innerHTML);
    tableBody.innerHTML = '';

    roster.forEach(roster => renderTimeSlot(roster));
}

function renderTimeSlot(timeSlot) {
    const row = document.createElement('tr');
    
    row.appendChild(renderCell(timeSlot.id));
    row.appendChild(renderCell(timeSlot.date));
    row.appendChild(renderCell(timeSlot.startTime));
    row.appendChild(renderCell(timeSlot.endTime));
    row.appendChild(renderCell(timeSlot.role));
    row.appendChild(renderCell(renderUser(timeSlot.user)));
    
    document.querySelector('#rosterTableBody').appendChild(row);

}

function renderCell(content) {
    const cell = document.createElement('td');
    cell.textContent = content;
    return cell;
}

function renderUser(user) {
    console.log(user);
    if (!user) {
        return 'NaN';
    }
    return user.name;
}

async function getDate() {
    const dateControl = document.querySelector('input[type="date"]');
    // console.log('getDate test prints');
    // console.log(dateControl.value); // gets date
    // console.log(dateControl.valueAsNumber); // gets epoch
    
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

