class Header extends HTMLElement {
    connectedCallback() {
        this.innerHTML = `
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
        </style>

      <header>
        <img src="/img/logo.png" alt="Logo">
        <nav>
          <ul>
            <li><a href="../html/employee-index.html">Hjem</a></li>
            <li><a href="../html/snack-manager.html">Snack</a></li>
            <li><a href="../html/shows.html">Show admin</a></li>
            <li><a href="../html/movieManager.html">Film admin</a></li>
            <li><a href="../html/roster.html">Vagtplan</a></li>
          </ul>
        </nav>
      </header>
    `;
    }
}

customElements.define('employee-header', Header);
