class Footer extends HTMLElement{
    constructor() {
        super();
    }

    connectedCallback(){
        this.innerHTML =`

<footer>
    <p>&copy; 2025 KinoXP Cinema</p>
    <p> Kirkehelle 5, Vissenbjerg, DK 5492 | KinoXP@info.com </p>
</footer>
        `;
    }
}

customElements.define('employee-footer', Footer)