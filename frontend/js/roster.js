document.addEventListener("DOMContentLoaded", initApp);

const rosterUrl = 'http://localhost:8080/api/roster';
const calender = document.querySelector("#date");
let date = new Date;
let rosters;

async function initApp() {
    console.log('date: ' + date);
    calender.addEventListener('input', getDate);
    console.log(rosterUrl);
    rosters = await fetchRoster();
    console.log('roster: ' + rosters);
    renderRosters(rosters);
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
    console.log('roster fetched');
    rosters = await response.json();
    console.log(rosters);
    return rosters;
}

function renderRosters() {
    const tableBody = document.querySelector('#rosterTableBody');
    console.log('tableBody innterHTML: ' + tableBody.innerHTML);
    tableBody.innerHTML = '';

    rosters.forEach(roster => renderRoster(roster));
}

function renderRoster(roster) {
    const row = document.createElement('tr');
    
    row.appendChild(renderCell(roster.id));
    row.appendChild(renderCell(roster.date));
    row.appendChild(renderCell(roster.startTime));
    row.appendChild(renderCell(roster.endTime));
    row.appendChild(renderCell(roster.role));
    row.appendChild(renderCell(renderUser(roster.user)));
    
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
    renderRosters(rosters);
}

