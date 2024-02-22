const header = document.getElementById("header");
let prevScrollPos = window.pageYOffset;

window.onscroll = () => {
    const currentScrollPos = window.pageYOffset;
    
    if (prevScrollPos > currentScrollPos) {
        header.style.top = "0";
    } else {
        header.style.top = `-${header.offsetHeight}px`;
    }
    
    prevScrollPos = currentScrollPos;
};

const possibleLoginNames = ["Charaf", "Mariya", "Jenna"]
function access() {
    let admin = "N"
    if (admin == "Y") {
        admin = true
    }
    else {
        admin = false
    }
    let loginName = "Char"
    let password = 125

    if (!admin) {
        return "You are not admin."
    }
  
    if (!possibleLoginNames.includes(loginName)) {
        return "Either Name or password is incorrect.";
    }
   
    if (password!= 123) {
        return "Either Name or password is incorrect."
    }
    

}

function fillField() {
    const field = document.getElementById("field")
    field.innerHTML = access()
}

   