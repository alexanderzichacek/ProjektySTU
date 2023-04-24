function getAge() {
    const birthDay = new Date(document.getElementById("dateInput").value);
    const month_difference = Date.now() - birthDay.getTime();
    const age_dt = new Date(month_difference);
    const year = age_dt.getUTCFullYear();
    return Math.abs(year - 1970);
}

function showArea() {
    if (document.getElementById("poziadavky").style.display === "block") {
        document.getElementById("poziadavky").style.display = "none";
    } else {
        document.getElementById("poziadavky").style.display = "block";
    }
}

function showTerm() {
    if (document.getElementById("inputTerm").style.display === "block") {
        document.getElementById("inputTerm").style.display = "none";
        document.getElementById("labelTerm").style.display = "none";
    } else {
        document.getElementById("inputTerm").style.display = "block";
        document.getElementById("labelTerm").style.display = "block";
    }
}

function showOutdoor() {
    if (document.getElementById("firstOutdoor").style.display === "none") {
        document.getElementById("firstOutdoor").style.display = "inline-block";
        document.getElementById("secondOutdoor").style.display = "inline-block";
        document.getElementById("thirdOutdoor").style.display = "inline-block";
        document.getElementById("firstIndoor").style.display = "none";
        document.getElementById("secondIndoor").style.display = "none";
        document.getElementById("thirdIndoor").style.display = "none";
    } else {
        document.getElementById("firstOutdoor").style.display = "inline-block";
        document.getElementById("secondOutdoor").style.display = "inline-block";
        document.getElementById("thirdOutdoor").style.display = "inline-block";
        document.getElementById("firstIndoor").style.display = "none";
        document.getElementById("secondIndoor").style.display = "none";
        document.getElementById("thirdIndoor").style.display = "none";
    }
}

function showIndoor() {
    if (document.getElementById("firstIndoor").style.display === "none") {
        document.getElementById("firstIndoor").style.display = "inline-block";
        document.getElementById("secondIndoor").style.display = "inline-block";
        document.getElementById("thirdIndoor").style.display = "inline-block";
        document.getElementById("firstOutdoor").style.display = "none";
        document.getElementById("secondOutdoor").style.display = "none";
        document.getElementById("thirdOutdoor").style.display = "none";
    } else {
        document.getElementById("firstIndoor").style.display = "inline-block";
        document.getElementById("secondIndoor").style.display = "inline-block";
        document.getElementById("thirdIndoor").style.display = "inline-block";
        document.getElementById("firstOutdoor").style.display = "none";
        document.getElementById("secondOutdoor").style.display = "none";
        document.getElementById("thirdOutdoor").style.display = "none";
    }
}

class IntlCodeSelect {
    static menuDisplayFn(item) {
        return item;
    }

    static displayFn(item) {
        return item;
    }

    static isDefault(item, i) {
        return i === 0;
    }

    constructor(elem, codeList) {
        this.menuElem = elem.querySelector('.select-menu');
        this.displayElem = elem.querySelector('.select-display');

        this.codeList = codeList || [];

        this.__initListeners();
        if (codeList && codeList.length) {
            this.initMenu(codeList);
        }
    }

    __initListeners() {
        const {menuElem} = this;
        menuElem.addEventListener('change', () => {
            this.updateDisplayByIndex(menuElem.value);
        });
    }

    initMenu(codeList = []) {
        const {menuElem} = this;

        this.codeList = codeList;

        codeList.forEach((item, i) => {
            let isDefault = IntlCodeSelect.isDefault(item, i);
            let opt = new Option(IntlCodeSelect.menuDisplayFn(item), i, false, isDefault);
            menuElem.add(opt);

            if (isDefault) {
                this.updateDisplayByIndex(i);
            }
        });

    }

    updateDisplayByIndex(i) {
        const {codeList, displayElem} = this;
        const item = codeList[i];
        let text = '';
        if (item != null) {
            text = IntlCodeSelect.displayFn(item);
        }

        displayElem.textContent = text;
    }

    onChange(fn) {
        const {menuElem} = this;
        menuElem.addEventListener('change', () => fn(menuElem.value));
    }
}


class IntlPhoneInput {
    constructor(elem, codeList) {
        this.selectElem = elem.querySelector('.intl-code-select');

        this.selectCtrl = new IntlCodeSelect(this.selectElem, codeList);
    }
}

let areaCodeList = [
    {
        id: 1,
        show: '+421',
        area: 'Slovensko (Slovenská republika)',
        defaultSelected: true
    },

    {
        id: 2,
        show: '+420',
        area: 'Česko'
    },

    {
        id: 3,
        show: '+43',
        area: 'Rakúsko'
    }
];

IntlCodeSelect.menuDisplayFn = function (item) {
    return `${item.area} (${item.show})`;
};
IntlCodeSelect.displayFn = function (item) {
    return item.show;
};
IntlCodeSelect.isDefault = function (item) {
    return item.defaultSelected || false;
};

const phoneInputElem = document.querySelector('.intl-phone-input');
const phoneCtrl = new IntlPhoneInput(phoneInputElem, areaCodeList);

function requiredName() {
    const inputText = document.getElementById('nameInput');
    const verification = /^[a-zA-Zá-žÁ-Ž]+$/;
    if (inputText.value === '') {
        inputText.style.border = "2px dotted darkred";
        inputText.style.background = "palevioletred";
        document.getElementById("nameP").style.visibility = "visible";
        return false;
    } else if (!inputText.value.match(verification)) {
        inputText.style.border = "2px dotted darkred";
        inputText.style.background = "palevioletred";
        document.getElementById("nameP").style.visibility = "visible";
        document.getElementById("nameP").innerHTML = "Meno nie je validné";
        document.getElementById("nameP").style.left = "38%";
        return false;
    } else {
        inputText.style.border = "none";
        inputText.style.borderBottom = "1px solid #c6c6c6";
        inputText.style.background = "none";
        document.getElementById("nameP").style.visibility = "hidden";
        return true;
    }
}

function requiredSurname() {
    const inputText = document.getElementById('surnameInput');
    if (inputText.value === '') {
        inputText.style.border = "2px dotted darkred";
        inputText.style.background = "palevioletred";
        document.getElementById("surnameP").style.visibility = "visible";
        return false;
    } else {
        inputText.style.border = "none";
        inputText.style.borderBottom = "1px solid #c6c6c6";
        inputText.style.background = "none";
        document.getElementById("surnameP").style.visibility = "hidden";
        return true;
    }
}

function requiredDate() {
    const inputText = document.getElementById('dateInput');
    if (inputText.value === '') {
        inputText.style.border = "2px dotted darkred";
        inputText.style.background = "palevioletred";
        document.getElementById("dateP").style.visibility = "visible";
        return false;
    } else {
        inputText.style.border = "none";
        inputText.style.borderBottom = "1px solid #c6c6c6";
        inputText.style.background = "none";
        document.getElementById("dateP").style.visibility = "hidden";

        document.getElementById("ageInput").value = getAge();
        document.getElementById("ageInput").style.border = "none";
        document.getElementById("ageInput").style.borderBottom = "1px solid #c6c6c6";
        document.getElementById("ageInput").style.background = "none";
        document.getElementById("ageP").style.visibility = "hidden";
        return true;
    }
}

function requiredAge() {
    const inputText = document.getElementById('ageInput');
    if (inputText.value === '') {
        inputText.style.border = "2px dotted darkred";
        inputText.style.background = "palevioletred";
        document.getElementById("ageP").style.visibility = "visible";
        return false;
    } else if (inputText.value != getAge()) {
        inputText.style.border = "2px dotted darkred";
        inputText.style.background = "palevioletred";
        document.getElementById("ageP").style.visibility = "visible";
        document.getElementById("ageP").innerHTML = "Vek sa nezhoduje s dĂˇtumom narodenia!";
        document.getElementById("ageP").style.left = "18%";
        return false;
    } else {
        inputText.style.border = "none";
        inputText.style.borderBottom = "1px solid #c6c6c6";
        inputText.style.background = "none";
        document.getElementById("ageP").style.visibility = "hidden";
        return true;
    }
}

function requiredGender() {
    const inputText = document.getElementById('genderInput');
    if (inputText.value === '') {
        inputText.style.border = "2px dotted darkred";
        inputText.style.background = "palevioletred";
        document.getElementById("genderP").style.visibility = "visible";
        return false;
    } else {
        inputText.style.border = "none";
        inputText.style.borderBottom = "1px solid #c6c6c6";
        inputText.style.background = "#0d8aee";
        document.getElementById("genderP").style.visibility = "hidden";
        return true;
    }
}

function requiredEmail() {
    const inputText = document.getElementById('emailInput');
    const verification = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,4}))$/;

    if (inputText.value === '') {
        inputText.style.border = "2px dotted darkred";
        inputText.style.background = "palevioletred";
        document.getElementById("emailP").style.visibility = "visible";
        return false;
    } else if (!inputText.value.match(verification)) {
        inputText.style.border = "2px dotted darkred";
        inputText.style.background = "palevioletred";
        document.getElementById("emailP").style.visibility = "visible";
        document.getElementById("emailP").innerHTML = "Email nie je validný";
        document.getElementById("emailP").style.left = "47%";
        return false;
    } else {
        inputText.style.border = "none";
        inputText.style.borderBottom = "1px solid #c6c6c6";
        inputText.style.background = "none";
        document.getElementById("emailP").style.visibility = "hidden";
        return true;
    }
}

function onlyNumberKey(evt) {
    const ASCIICode = (evt.which) ? evt.which : evt.keyCode;
    return !(ASCIICode > 31 && (ASCIICode < 48 || ASCIICode > 57));
}

const outdoorObjects = {
    "Tenis": {
        "Loptička": ["1 hodina", "2 hodiny", "1/2 hodina"],
        "Raketa + loptička": ["1 hodina", "2 hodiny", "1/2 hodina"],
        "Nič": ["1 hodina", "2 hodiny", "1/2 hodina"]
    },
    "Futbal": {
        "Lopta": ["90 minút", "180 minút"],
        "Nič": ["90 minút", "180 minút"]
    },
    "Plážový volejbal": {
        "Lopta": ["1 hodina", "2 hodiny", "1/2 hodina"],
        "Nič": ["1 hodina", "2 hodiny", "1/2 hodina"]
    }
};

const indoorObjects = {
    "Ping-pong": {
        "1 raketa": ["1/2 hodina", "1 hodina"],
        "2 rakety": ["1/2 hodina", "1 hodina"],
        "Loptička": ["1/2 hodina", "1 hodina"],
        "1 raketa + loptička": ["1/2 hodina", "1 hodina"],
        "2 rakety + loptička": ["1/2 hodina", "1 hodina"],
        "Nič": ["1/2 hodina", "1 hodina"]
    },
    "Florbal": {
        "Hokejka": ["1 hodina", "2 hodiny", "3 hodiny"],
        "Loptička": ["1 hodina", "2 hodiny", "3 hodiny"],
        "Hokejka + loptička": ["1 hodina", "2 hodiny", "3 hodiny"],
        "Nič": ["1 hodina", "2 hodiny", "3 hodiny"]
    },
    "Squash": {
        "Loptička": ["1 set", "2 sety", "1 hodina", "2 hodiny"],
        "Raketa + loptička": ["1 set", "2 sety", "1 hodina", "2 hodiny"],
        "Nič": ["1 set", "2 sety", "1 hodina", "2 hodiny"]
    }
};

window.onload = function () {
    const sportTypeOut = document.getElementById("firstOutdoor");
    const equipmentOut = document.getElementById("secondOutdoor");
    const timeOut = document.getElementById("thirdOutdoor");

    for (const x in outdoorObjects) {
        sportTypeOut.options[sportTypeOut.options.length] = new Option(x, x);
    }
    sportTypeOut.onchange = function () {
        timeOut.length = 1;
        equipmentOut.length = 1;
        for (const y in outdoorObjects[this.value]) {
            equipmentOut.options[equipmentOut.options.length] = new Option(y, y);
        }
    };
    equipmentOut.onchange = function () {
        timeOut.length = 1;
        const z = outdoorObjects[sportTypeOut.value][this.value];
        for (let i = 0; i < z.length; i++) {
            timeOut.options[timeOut.options.length] = new Option(z[i], z[i]);
        }
    };

    const sportTypeIn = document.getElementById("firstIndoor");
    const equipmentIn = document.getElementById("secondIndoor");
    const timeIn = document.getElementById("thirdIndoor");

    for (const x in indoorObjects) {
        sportTypeIn.options[sportTypeIn.options.length] = new Option(x, x);
    }
    sportTypeIn.onchange = function () {
        timeIn.length = 1;
        equipmentIn.length = 1;
        for (const y in indoorObjects[this.value]) {
            equipmentIn.options[equipmentIn.options.length] = new Option(y, y);
        }
    };
    equipmentIn.onchange = function () {
        timeIn.length = 1;
        const z = indoorObjects[sportTypeIn.value][this.value];
        for (let i = 0; i < z.length; i++) {
            timeIn.options[timeIn.options.length] = new Option(z[i], z[i]);
        }
    };
};

function sum() {
    const sportTypeOut = document.getElementById("firstOutdoor");
    const equipmentOut = document.getElementById("secondOutdoor");
    const timeOut = document.getElementById("thirdOutdoor");

    const sportTypeIn = document.getElementById("firstIndoor");
    const equipmentIn = document.getElementById("secondIndoor");
    const timeIn = document.getElementById("thirdIndoor");

    const sum = document.getElementById("sum");

    if (sportTypeOut.value === "Tenis" && equipmentOut.value === "Loptička" && timeOut.value === "1 hodina") {
        sum.innerHTML = "Cena: 10€";
    }
    if (sportTypeOut.value === "Tenis" && equipmentOut.value === "Loptička" && timeOut.value === "2 hodiny") {
        sum.innerHTML = "Cena: 20€";
    }
    if (sportTypeOut.value === "Tenis" && equipmentOut.value === "Loptička" && timeOut.value === "1/2 hodina") {
        sum.innerHTML = "Cena: 5€";
    }
    if (sportTypeOut.value === "Tenis" && equipmentOut.value === "Raketa + loptička" && timeOut.value === "1 hodina") {
        sum.innerHTML = "Cena: 15€";
    }
    if (sportTypeOut.value === "Tenis" && equipmentOut.value === "Raketa + loptička" && timeOut.value === "2 hodiny") {
        sum.innerHTML = "Cena: 30€";
    }
    if (sportTypeOut.value === "Tenis" && equipmentOut.value === "Raketa + loptička" && timeOut.value === "1/2 hodina") {
        sum.innerHTML = "Cena: 7.5€";
    }
    if (sportTypeOut.value === "Tenis" && equipmentOut.value === "Nič" && timeOut.value === "1 hodina") {
        sum.innerHTML = "Cena: 6€";
    }
    if (sportTypeOut.value === "Tenis" && equipmentOut.value === "Nič" && timeOut.value === "2 hodiny") {
        sum.innerHTML = "Cena: 12€";
    }
    if (sportTypeOut.value === "Tenis" && equipmentOut.value === "Nič" && timeOut.value === "1/2 hodina") {
        sum.innerHTML = "Cena: 3€";
    }

    if (sportTypeOut.value === "Futbal" && equipmentOut.value === "Nič" && timeOut.value === "90 minút") {
        sum.innerHTML = "Cena: 5€";
    }
    if (sportTypeOut.value === "Futbal" && equipmentOut.value === "Lopta" && timeOut.value === "90 minút") {
        sum.innerHTML = "Cena: 10€";
    }
    if (sportTypeOut.value === "Futbal" && equipmentOut.value === "Nič" && timeOut.value === "180 minút") {
        sum.innerHTML = "Cena: 8€";
    }
    if (sportTypeOut.value === "Futbal" && equipmentOut.value === "Lopta" && timeOut.value === "180 minút") {
        sum.innerHTML = "Cena: 16€";
    }

    if (sportTypeOut.value === "Plážový volejbal" && equipmentOut.value === "Lopta" && timeOut.value === "1 hodina") {
        sum.innerHTML = "Cena: 6€";
    }
    if (sportTypeOut.value === "Plážový volejbal" && equipmentOut.value === "Lopta" && timeOut.value === "2 hodiny") {
        sum.innerHTML = "Cena: 12€";
    }
    if (sportTypeOut.value === "Plážový volejbal" && equipmentOut.value === "Lopta" && timeOut.value === "1/2 hodina") {
        sum.innerHTML = "Cena: 3€";
    }
    if (sportTypeOut.value === "Plážový volejbal" && equipmentOut.value === "Nič" && timeOut.value === "1 hodina") {
        sum.innerHTML = "Cena: 4€";
    }
    if (sportTypeOut.value === "Plážový volejbal" && equipmentOut.value === "Nič" && timeOut.value === "2 hodiny") {
        sum.innerHTML = "Cena: 8€";
    }
    if (sportTypeOut.value === "Plážový volejbal" && equipmentOut.value === "Nič" && timeOut.value === "1/2 hodina") {
        sum.innerHTML = "Cena: 2€";
    }

    if (sportTypeIn.value === "Ping-pong" && equipmentIn.value === "1 raketa" && timeIn.value === "1/2 hodina") {
        sum.innerHTML = "Cena: 5€";
    }
    if (sportTypeIn.value === "Ping-pong" && equipmentIn.value === "1 raketa" && timeIn.value === "1 hodina") {
        sum.innerHTML = "Cena: 7.5€";
    }
    if (sportTypeIn.value === "Ping-pong" && equipmentIn.value === "2 rakety" && timeIn.value === "1/2 hodina") {
        sum.innerHTML = "Cena: 8€";
    }
    if (sportTypeIn.value === "Ping-pong" && equipmentIn.value === "2 rakety" && timeIn.value === "1 hodina") {
        sum.innerHTML = "Cena: 10€";
    }
    if (sportTypeIn.value === "Ping-pong" && equipmentIn.value === "Loptička" && timeIn.value === "1/2 hodina") {
        sum.innerHTML = "Cena: 2€";
    }
    if (sportTypeIn.value === "Ping-pong" && equipmentIn.value === "Loptička" && timeIn.value === "1 hodina") {
        sum.innerHTML = "Cena: 4€";
    }
    if (sportTypeIn.value === "Ping-pong" && equipmentIn.value === "1 raketa + loptička" && timeIn.value === "1/2 hodina") {
        sum.innerHTML = "Cena: 8€";
    }
    if (sportTypeIn.value === "Ping-pong" && equipmentIn.value === "1 raketa + loptička" && timeIn.value === "1 hodina") {
        sum.innerHTML = "Cena: 12€";
    }
    if (sportTypeIn.value === "Ping-pong" && equipmentIn.value === "2 rakety + loptička" && timeIn.value === "1/2 hodina") {
        sum.innerHTML = "Cena: 10€";
    }
    if (sportTypeIn.value === "Ping-pong" && equipmentIn.value === "2 rakety + loptička" && timeIn.value === "1 hodina") {
        sum.innerHTML = "Cena: 15€";
    }
    if (sportTypeIn.value === "Ping-pong" && equipmentIn.value === "Nič" && timeIn.value === "1/2 hodina") {
        sum.innerHTML = "Cena: 1€";
    }
    if (sportTypeIn.value === "Ping-pong" && equipmentIn.value === "Nič" && timeIn.value === "1 hodina") {
        sum.innerHTML = "Cena: 2€";
    }

    if (sportTypeIn.value === "Florbal" && equipmentIn.value === "Hokejka" && timeIn.value === "1 hodina") {
        sum.innerHTML = "Cena: 15€";
    }
    if (sportTypeIn.value === "Florbal" && equipmentIn.value === "Hokejka" && timeIn.value === "2 hodiny") {
        sum.innerHTML = "Cena: 20€";
    }
    if (sportTypeIn.value === "Florbal" && equipmentIn.value === "Hokejka" && timeIn.value === "3 hodiny") {
        sum.innerHTML = "Cena: 25€";
    }
    if (sportTypeIn.value === "Florbal" && equipmentIn.value === "Loptička" && timeIn.value === "1 hodina") {
        sum.innerHTML = "Cena: 10€";
    }
    if (sportTypeIn.value === "Florbal" && equipmentIn.value === "Loptička" && timeIn.value === "2 hodiny") {
        sum.innerHTML = "Cena: 13€";
    }
    if (sportTypeIn.value === "Florbal" && equipmentIn.value === "Loptička" && timeIn.value === "3 hodiny") {
        sum.innerHTML = "Cena: 15€";
    }
    if (sportTypeIn.value === "Florbal" && equipmentIn.value === "Hokejka + loptička" && timeIn.value === "1 hodina") {
        sum.innerHTML = "Cena: 20€";
    }
    if (sportTypeIn.value === "Florbal" && equipmentIn.value === "Hokejka + loptička" && timeIn.value === "2 hodiny") {
        sum.innerHTML = "Cena: 25€";
    }
    if (sportTypeIn.value === "Florbal" && equipmentIn.value === "Hokejka + loptička" && timeIn.value === "3 hodiny") {
        sum.innerHTML = "Cena: 28€";
    }
    if (sportTypeIn.value === "Florbal" && equipmentIn.value === "Nič" && timeIn.value === "1 hodina") {
        sum.innerHTML = "Cena: 5€";
    }
    if (sportTypeIn.value === "Florbal" && equipmentIn.value === "Nič" && timeIn.value === "2 hodiny") {
        sum.innerHTML = "Cena: 7€";
    }
    if (sportTypeIn.value === "Florbal" && equipmentIn.value === "Nič" && timeIn.value === "3 hodiny") {
        sum.innerHTML = "Cena: 10€";
    }

    if (sportTypeIn.value === "Squash" && equipmentIn.value === "Loptička" && timeIn.value === "1 set") {
        sum.innerHTML = "Cena: 3€";
    }
    if (sportTypeIn.value === "Squash" && equipmentIn.value === "Loptička" && timeIn.value === "2 sety") {
        sum.innerHTML = "Cena: 5€";
    }
    if (sportTypeIn.value === "Squash" && equipmentIn.value === "Loptička" && timeIn.value === "1 hodina") {
        sum.innerHTML = "Cena: 8€";
    }
    if (sportTypeIn.value === "Squash" && equipmentIn.value === "Loptička" && timeIn.value === "2 hodiny") {
        sum.innerHTML = "Cena: 15€";
    }
    if (sportTypeIn.value === "Squash" && equipmentIn.value === "Raketa + loptička" && timeIn.value === "1 set") {
        sum.innerHTML = "Cena: 5€";
    }
    if (sportTypeIn.value === "Squash" && equipmentIn.value === "Raketa + loptička" && timeIn.value === "2 sety") {
        sum.innerHTML = "Cena: 9€";
    }
    if (sportTypeIn.value === "Squash" && equipmentIn.value === "Raketa + loptička" && timeIn.value === "1 hodina") {
        sum.innerHTML = "Cena: 15€";
    }
    if (sportTypeIn.value === "Squash" && equipmentIn.value === "Raketa + loptička" && timeIn.value === "2 hodiny") {
        sum.innerHTML = "Cena: 23€";
    }
    if (sportTypeIn.value === "Squash" && equipmentIn.value === "Nič" && timeIn.value === "1 set") {
        sum.innerHTML = "Cena: 2€";
    }
    if (sportTypeIn.value === "Squash" && equipmentIn.value === "Nič" && timeIn.value === "2 sety") {
        sum.innerHTML = "Cena: 3€";
    }
    if (sportTypeIn.value === "Squash" && equipmentIn.value === "Nič" && timeIn.value === "1 hodina") {
        sum.innerHTML = "Cena: 5€";
    }
    if (sportTypeIn.value === "Squash" && equipmentIn.value === "Nič" && timeIn.value === "2 hodiny") {
        sum.innerHTML = "Cena: 8€";
    }
}

var modal = document.getElementById("myModal");

var btn = document.getElementById("myBtn");

var span = document.getElementsByClassName("close")[0];

btn.onclick = function () {
    document.getElementById("nameModal").innerHTML = document.getElementById("nameInput").value;
    document.getElementById("surnameModal").innerHTML = document.getElementById("surnameInput").value;
    document.getElementById("dateModal").innerHTML = document.getElementById("dateInput").value;
    document.getElementById("ageModal").innerHTML = document.getElementById("ageInput").value;
    document.getElementById("genderModal").innerHTML = document.getElementById("genderInput").value;
    document.getElementById("emailModal").innerHTML = document.getElementById("emailInput").value;
    document.getElementById("phoneModal").innerHTML = document.getElementById("phoneId").value;
    if (document.getElementById("outdoor").checked) {
        document.getElementById("activityModal").innerHTML =
            document.getElementById("firstOutdoor").value + " / " + document.getElementById("secondOutdoor").value + " / " + document.getElementById("thirdOutdoor").value;
    }
    if (document.getElementById("indoor").checked) {
        document.getElementById("activityModal").innerHTML =
            document.getElementById("firstIndoor").value + " / " + document.getElementById("secondIndoor").value + " / " + document.getElementById("thirdIndoor").value;
    }
    document.getElementById("specialModal").innerHTML = document.getElementById("poziadavky").value;
    document.getElementById("termModal").innerHTML = document.getElementById("inputTerm").value;
    document.getElementById("prizeModal").innerHTML = document.getElementById("sum").outerText;

    if (requiredName() && requiredSurname() && requiredDate() && requiredAge() && requiredGender() && requiredEmail()) modal.style.display = "block";
    else modal.style.display = "none";
};

span.onclick = function () {
    modal.style.display = "none";
};

window.onclick = function (event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
};