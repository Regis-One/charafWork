class Person {
    constructor(firstName, lastName, age){
            this.fullName = firstName + " " + lastName ;
            this.age = age;
    }
    presentYourself() {
        console.log("Hello, I am " + this.fullName + ", I am " + this.age+ " years old.")
    }
}

class Worker extends Person{
    constructor(firstName, lastName, age, id, recruitmentYear) {
        super(firstName, lastName, age);
        this.id = id;
        this.xpYears = 2023 - recruitmentYear
    }
    presentYourself(){
        console.log("Hello, I am "+ this.fullName + ", I am " + this.age + " years old.")
        console.log("Badge# " + this.id);
        console.log("I have been working for this company for "+ this.xpYears + " years now.")
    }
    }
    

var worker= new Worker("Charaf", "IDRISSI", 32, "AB25", 2000);
var human = new Person("Karim", "Sqali", 44)
var working = new Worker("Yasmina", "DHISSI", 30, "XB25", 1970);
human.presentYourself()
console.log("********************")
worker.presentYourself()
console.log("-----------------------------")
working.presentYourself()

