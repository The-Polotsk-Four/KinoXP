class Header extends HTMLElement {
    constructor() {
        super();
    }

    connectedCallback() {
        this.innerHTML = `
        <style>
            header {
                display: flex;
                align-items: center;
                justify-content: space-between;
                background-color: #000;
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

            nav ul li a, .login-btn {
                text-decoration: none;
                color: #eee;
                font-weight: bold;
                transition: color 0.3s;
                background: none;
                border: none;
                cursor: pointer;
                font-size: 16px;
            }

            nav ul li a:hover, .login-btn:hover {
                color: #ffcc00;
            }

            /* Popup styles */
            .overlay-container {
                display: none;
                position: fixed;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                background: rgba(0, 0, 0, 0.6);
                justify-content: center;
                align-items: center;
                opacity: 0;
                transition: opacity 0.3s ease;
                z-index: 1000;
            }
            .overlay-container.show {
                display: flex;
                opacity: 1;
            }
            .popup-box {
                background: #fff;
                padding: 24px;
                border-radius: 12px;
                box-shadow: 0 0 20px rgba(0,0,0,0.4);
                width: 320px;
                text-align: center;
                animation: fadeInUp 0.5s ease-out forwards;
            }
            @keyframes fadeInUp {
                from { opacity: 0; transform: translateY(20px); }
                to { opacity: 1; transform: translateY(0); }
            }
            .form-container {
                display: flex;
                flex-direction: column;
            }
            .form-input {
                padding: 10px;
                margin-bottom: 20px;
                border: 1px solid #ccc;
                border-radius: 8px;
                font-size: 16px;
            }
            .btn-submit, .btn-close-popup {
                padding: 12px;
                border: none;
                border-radius: 8px;
                cursor: pointer;
            }
            .btn-submit { background: green; color: #fff; }
            .btn-close-popup { margin-top: 12px; background: #e74c3c; color: #fff; }
        </style>

        <header>
            <img src="/img/logo.png" alt="Logo">
            <nav>
                <ul>
                    <li><a href="/html/index.html">Hjem</a></li>
                    <li><a href="/html/viewSnacks.html">Snack Udvalg</a></li>
                    <li><a href="/html/shows.html">I biografen nu</a></li>
                    <li><a href="#">Kommer snart</a></li>
                    <li><a href="#">Billetter</a></li>
                    <li><a href="/html/contact-info.html">Kontakt</a></li>
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

customElements.define('header-component', Header);

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