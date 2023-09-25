import React from "react"
import Navbar from "./components/Navbar"
import Hero from "./components/Hero"
import Card from "./components/Card"
function App() {
  return (
    <div >
           <Navbar />
           <Hero/>
    <div className="card">
      <Card
          img = "./snow.jpg"
          rating = "5"
          number_reviews = "(17)"
          country = "CAN"
          price = "$2500 /person"
            />
      <Card
          rating = "3"
          number_reviews = "(25)"
          country = "MAR"
          img = "./desert.jpg"

            />
      <Card
          img = "./forest.jpg"
          rating = "5"
          number_reviews = "(12)"
          country = "DEU"
          price = "$1750 /person"
            />

    </div>
  
    </div>
    
  

  );
}

export default App;
