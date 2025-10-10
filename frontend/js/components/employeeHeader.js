class Header extends HTMLElement {
    constructor() {
        super();
    }

    connectedCallback() {
        this.innerHTML = `
<<<<<<< HEAD
       <style>
            header {
                display: flex;
                align-items: center;
                justify-content: space-between;
                background-color: #17181b;
                padding: 15px 30px;
            }

            header img {
                height: 50px;
            }

            nav ul {
                list-style: none;
                margin: 0;
                padding: 0;
                display: flex;
                gap: 20px;
            }

            nav ul li a {
                text-decoration: none;
                color: #eee;
                font-weight: bold;
                transition: color 0.3s;
                background: none;
                border: none;
                cursor: pointer;
                font-size: 16px;
            }

            nav ul li a:hover {
                color: #ffcc00;
            }
=======
        <style>
            
>>>>>>> employeeheader
        </style>

      <header>
        <img src="/img/logo.png" alt="Logo">
        <nav>
          <ul>
            <li><a href="../html/employee-index.html">Hjem</a></li>
            <li><a href="../html/snack-manager.html">Snack</a></li>
            <li><a href="../html/employeeMovies.html">Show admin</a></li>
            <li><a href="../html/movieManager.html">Film admin</a></li>
            <li><a href="../html/roster.html">Vagtplan</a></li>
            <li><button class="login-btn" onclick="togglePopup()">Login</button></li>
          </ul>
        </nav>
      </header>
   
    
<div id="popupOverlay" class="overlay-container">
    <div class="popup-box">
        <h2>Login</h2>
        <form class="form-container" onsubmit="handleLogin(event)">
            <input class="form-input" type="email" placeholder="Email" required>
                <input class="form-input" type="password" placeholder="Password" required>
                    <button class="btn-submit" type="submit">Login</button>
        </form>
        <button class="btn-close-popup" onclick="togglePopup()">Close</button>
    </div>
</div>
        `;
    }
}

customElements.define('employee-header', Header);

window.togglePopup = function() {
const overlay = document.getElementById('popupOverlay');
overlay.classList.toggle('show');
};

window.handleLogin = async function(event) {
event.preventDefault();

const email = event.target.querySelector('input[type="email"]').value;
const password = event.target.querySelector('input[type="password"]').value;

try {
    const response = await fetch("http://localhost:8080/api/users/login", {
        method: "POST",
        headers: { "Content-Type": "application/x-www-form-urlencoded" },
        body: new URLSearchParams({ email, password })
    });

    if (response.ok) {
        alert("Login successful!");
        togglePopup();

        const loginBtn = document.querySelector(".login-btn");
        if (loginBtn) {
            loginBtn.textContent = "Logout";
            loginBtn.onclick = () => {
                localStorage.removeItem("user");
                alert("You've been logged out.");
                loginBtn.textContent = "Login";
                loginBtn.onclick = togglePopup;
            };
        }

    } else {
        const message = await response.text();
        alert("Login failed: " + message);
    }

} catch (error) {
    console.error("Error:", error);
    alert("Server error. Could not connect")
}
};