let computerGuess;
let userGuesses = [];
let low = 1;
let high = 100;
let space = 0;
function updateRange(){
    const lowValue = document.getElementById("low");
    lowValue.style.flex = low + "%";
    lowValue.style.background = "#ef7b54";

    const space = document.getElementById("space");
    space.style.flex = high - low + "%";
    space.style.background = "#83e1d0";

    const highValue = document.getElementById("high");
    highValue.style.flex = 100 - high + "%";
    highValue.style.background = "#ef7b54";
    const rangeOutput = document.getElementById("rangeOutput");
    rangeOutput.innerText = `${low}-${high}`;
    rangeOutput.style.marginLeft = low + "%"; 
    rangeOutput.style.marginRight = 100 - high + "%";
    rangeOutput.classList.add('flash'); 
}


function gameEnded() {
    document.getElementById("newGameButton").style.display = "inline";
    document.getElementById("inputBox").setAttribute('readonly', 'readonly');
}

function init(){
    computerGuess = Math.floor(Math.random()*100 +1);
    console.log(computerGuess);
    document.getElementById("newGameButton").style.display = "none";
    document.getElementById("gameArea").style.display = "none";
}

function reinit(){
    window.location.reload()
}

function startGameView(){
    document.getElementById("welcomeScreen").style.display = "none";
    document.getElementById("gameArea").style.display = "block";
}

function easyMode(){
    startGameView();
    maxGuesses = 10;
}
function hardMode(){
    startGameView();
    maxGuesses = 5;
}

function compareGuess() {
    const userGuess = Number(document.getElementById('inputBox').value);
    userGuesses.push(" "+userGuess);
    document.getElementById("attempts").innerHTML = userGuesses.length;
    document.getElementById("guesses").innerHTML = userGuesses;
if (document.getElementById("attempts").innerHTML < maxGuesses)
    if (userGuess > computerGuess) {
        high = userGuess;
        document.getElementById("textOutput").innerHTML = "Your guess is too high!"
        document.getElementById("inputBox").value= "";
        
    }
    else if (userGuess <  computerGuess) {
        low = userGuess;
        document.getElementById("textOutput").innerHTML = "Your guess is too low!"
        document.getElementById("inputBox").value= "";
        
    }
    else  {
        document.getElementById("textOutput").innerHTML = "Correct!"
        gameEnded();
        
    }
else
    {document.getElementById("textOutput").innerHTML = "Game Over!";
    gameEnded();
}
updateRange();    

}