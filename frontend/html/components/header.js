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

        nav ul li a {
            text-decoration: none;
            color: #eee;
            font-weight: bold;
            transition: color 0.3s;
        }

        nav ul li a:hover {
            color: #ffcc00;
        }
    </style>

    <header>
        <img src="../css/img/logo.png" alt="Logo">
        <nav>
            <ul>
                <li><a href="#">Hjem</a></li>
                <li><a href="#">I biografen nu</a></li>
                <li><a href="#">Kommer snart</a></li>
                <li><a href="#">Billetter</a></li>
                <li><a href="#">Kontakt</a></li>
            </ul>
        </nav>
    </header>
    `;
    }
}

customElements.define('header-component', Header);
