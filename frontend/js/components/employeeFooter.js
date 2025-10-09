class Footer extends HTMLElement{
    constructor() {
        super();
    }

    connectedCallback(){
        this.innerHTML =`

        <style>
        footer {
    background-color: #000;
    text-align: center;
    padding: 20px;
    margin-top: 40px;
    position: fixed;
    left: 0;
    bottom: 0;
    width: 100%;
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

customElements.define('employee-footer', Footer)