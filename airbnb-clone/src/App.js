import React from "react"
import Navbar from "./components/Navbar"
import Hero from "./components/Hero"
import Card from "./components/Card"
import Places from "./components/Places"
function App() {
  const cardElements = Places.map(place => {
    return <Card country = {place.country} rating = {place.rating} img = {place.img} 
            number_reviews = {place.number_reviews} price = {place.price} open_spots = {place.open_spots}/>
  })
  return (
    <div >
           <Navbar />
           <Hero/>
    <div className="cards_list">
          {cardElements}
    </div>
  
    </div>
    
  

  );
}

export default App;
