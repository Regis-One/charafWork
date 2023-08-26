let health = 4;
let xp = 0;
let gold = 100;
let level = 4;
let currentWeapon = 0;
let fighting;

let windowPlace = document.getElementById("window");
let buttonCave = document.getElementById("buttonCave");
let buttonStay = document.getElementById("buttonStay");
let optionOne = document.getElementById("option_one");
let optionTwo = document.getElementById("option_two");
let optionThree = document.getElementById("option_three");
let windowText = document.getElementById("windowText");



buttonCave.onclick = function () {
    optionOne.style.display = "block";
    optionOne.innerHTML = "⚔️fight";
    optionTwo.style.display = "block";
    optionTwo.innerHTML = "🏃🏻flee";
    windowText.innerHTML = "You are entering the cave";
    fight()

   
};

buttonStay.onclick = function () {
  
};

optionOne.onclick = function() {
    fight()
}
optionTwo.onclick = function() {
    windowPlace.style.backgroundColor = "silver";
    windowText.innerHTML = "You are fleeing the scence";
}


function fight() {
    createEnemy();
    if (level > monster.level) {
        windowPlace.style.backgroundColor = "green";
        windowText.innerHTML = "You faced: " + monster.name + "  You won the battle";
       
    }
    else {
        windowPlace.style.backgroundColor = "red";
        windowText.innerHTML = "'You faced: " + monster.name + "  You lost the battle'";

    }
}

function randomizeMonsterAppearance() {
    return Math.floor(Math.random() * monsters.length) + 1;
}

let monster;
function createEnemy() {
    return monster = monsters[randomizeMonsterAppearance()];
}

let monsters = [
    {
        "name": "Drogo",
        "type": "dragon",
        "level": 2
    },
    {
        "name": "Flulffy",
        "type": "animal",
        "level": 2
    },
    {
        "name": "Mermaid",
        "type": "freak",
        "level": 5
    },
    {
        "name": "Wizard",
        "type": "human",
        "level": 15
    }
];


