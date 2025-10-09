class Footer extends HTMLElement{
    constructor() {
        super();
    }

    connectedCallback(){
        this.innerHTML =`

        <style>
        footer {
    background-color: #17181b;
    text-align: center;
    padding: 20px;
    margin-top: 20px;
    left: 0;
    bottom: 0;
    top: auto;
    width: auto;
}

footer p {
    margin: 5px 0;
}
</style>

<footer>
    <p>&copy; 2025 KinoXP Cinema</p>
    <p> Kirkehelle 5, Vissenbjerg, DK 5492 | KinoXP@info.com </p>
</footer>
        `;
    }
}

customElements.define('footer-component', Footer)