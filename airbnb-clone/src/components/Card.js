import React from "react";

function Card(props) {
 
  return (
    <div className="card">
      {props.open_spots === 0 && <div className="card_badge"> Sold out </div>}
      <div>
        {props.img ? (
          <img className="card_image" src={props.img} alt="Card" />
        ) : (
          <img className="card_image" src="./no_picture.png" alt="No Picture" />
        )}
      </div>
      <div className="card_stats">
        <span>
          {props.rating}
          <img className="star_rating" src="./star.png" alt="Star Rating" />
        </span>
        <span className="number_reviews">{props.number_reviews}</span>
        <span className="card_stat_country">{props.country}</span>
        {props.price ? (
          <span className="price">{props.price}</span>
        ) : (
          <span className="price">Free</span>
        )}
      </div>
    </div>
  );
}

export default Card;
