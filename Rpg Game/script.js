function startGame() {
    const game = document.getElementsByTagName("main")[0];
    const health = document.getElementById("health");
    let healthValue = parseInt(health.innerHTML);
    const xp = document.getElementById("XP");
    xpValue = parseInt(xp.innerHTML);
    const gold = document.getElementById("gold");
    let goldValue = parseInt(gold.innerHTML);
    const level = 4;
    let currentWeapon = document.getElementById("currentWeapon");

    const windowPlace = document.getElementById("window");
    const button1 = document.getElementById("button1");
    const button2 = document.getElementById("button2");
    const button3 = document.getElementById("button3");
    const windowText = document.getElementById("windowText");

    const locations = [
        {
            name: "main",
            "button text": ["Enter cave", "Stay here", "Go to store"],
            "button functions": [enterCave, stay, goToStore],
        },
        {
            name: "cave",
            "button text": ["🗡️Fight", "🏃🏻Flee", "Go to store"],
            "button functions": [fight, flee, goToStore],
        },
        {
            name: "store",
            "button text": ["Buy health", "Buy weapons", "Leave store"],
            "button functions": [buyHealth, buyWeapons, mainSquare],
        },
        {
            name: "weaponery",
            "button text": ["Buy a hammer", "Buy a sword", "Leave weaponery"],
            "button functions": [buyHammer, buySword, mainSquare],
        },
        {
            name: "confirmWeapon",
            "button text": ["Yes", "No", "Go back"],
            "button functions": [confirmBuying, goToStore, mainSquare]
        }
       
    ];

    // Attach event listeners for navigation buttons
    button1.addEventListener("click", function() {
        locations[currentLocationIndex]["button functions"][0]();
    });
    button2.addEventListener("click", function() {
        locations[currentLocationIndex]["button functions"][1]();
    });
    button3.addEventListener("click", function() {
        locations[currentLocationIndex]["button functions"][2]();
    });

    let currentLocationIndex = 0;
    updateLocation(locations[currentLocationIndex]);
    //update location according to player's choice
    function updateLocation(location) {
        button1.innerHTML = location["button text"][0];
        button2.innerHTML = location["button text"][1];
        button3.innerHTML = location["button text"][2];
    }

    function mainSquare() {
        currentLocationIndex = 0;
        updateLocation(locations[currentLocationIndex]);
        windowText.innerHTML = "Back to home screen.";
    }

    function stay() {
        mainSquare();
        windowText.innerHTML = "You are staying.";
    }

    function enterCave() {
        currentLocationIndex = 1;
        updateLocation(locations[currentLocationIndex]);
        windowText.innerHTML = "You are inside the cave.";
    }

    function goToStore() {
        currentLocationIndex = 2;
        updateLocation(locations[currentLocationIndex]);
        windowText.innerHTML = "You are inside the store.";
    }

    function fight() {
        const monster = createEnemy();
        if (level > monster.level) {
            windowPlace.style.backgroundColor = "green";
            windowText.innerHTML = `You faced: ${monster.name}. You won the battle`;
            xpValue+=10;
            xp.innerHTML = xpValue;
        } else {
            windowPlace.style.backgroundColor = "red";
            windowText.innerHTML = `You faced: ${monster.name}. You lost the battle`;
            if (healthValue > 0) {
                healthValue--;
                health.innerHTML = healthValue;
            } else {
                gameOver();
            }
        }
    }

    function flee() {
        windowText.innerHTML = "You are fleeing the scene";
    }

    function createEnemy() {
        const randomIndex = Math.floor(Math.random() * monsters.length);
        return monsters[randomIndex];
    }

    function buyHealth() {
        windowText.innerHTML = "Health bought";
        if(goldValue > 0) {
            goldValue -= 10;
            gold.innerHTML = goldValue;
            healthValue ++;
            health.innerHTML = healthValue;
        }
        else {
            windowText.innerHTML = "You don't have enough coins"
        }
       
    }

    function buyWeapons() {
        currentLocationIndex = 3;
        updateLocation(locations[currentLocationIndex])
    }
let weaponIndex = 0;
  function buyHammer(){
    weaponIndex = 0;
    windowText.innerHTML = "The price of the item is "  + weapons[0].price + " gold";   
    currentLocationIndex = 4;
    updateLocation(locations[currentLocationIndex]);
    return weaponIndex;
  }
  function buySword(){
    weaponIndex = 1;
    windowText.innerHTML = "The price of the item is "  + weapons[1].price + " gold";   
    currentLocationIndex = 4;
    updateLocation(locations[currentLocationIndex]);
    return weaponIndex;
  }
 
   function confirmBuying() {
    if (goldValue >= weapons[weaponIndex].price) {
        goldValue-= weapons[weaponIndex].price;
        gold.innerHTML = goldValue;
        currentWeapon.innerHTML = weapons[weaponIndex].name;
        }
        else {
            windowText.innerHTML = "You don't have enough coins";
        }

   }
 
    function gameOver() {
        game.remove();
        // Add game over logic here
    }

    const monsters = [
        {
            name: "Drogo",
            type: "dragon",
            level: 2,
        },
        {
            name: "Fluffy",
            type: "animal",
            level: 2,
        },
        {
            name: "Mermaid",
            type: "freak",
            level: 5,
        },
        {
            name: "Wizard",
            type: "human",
            level: 15,
        },
    ];
}
let weapons = [
{
    name: "🔨",
    power: 50,
    price: 50,
    
},
 {
    name: "⚔️",
    power: 10, 
    price: 10,
 },

]

// Start the game when the DOM is fully loaded
document.addEventListener("DOMContentLoaded", startGame);
