package application;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class createNumberTextClock extends Text {
	private double angle;
	
	public createNumberTextClock(String text, double x, double y, float angle) {
	    super(text);
	    this.angle = angle;
        setFont(Font.font("Arial", FontWeight.BOLD, 20));
        setFill(Color.GOLD);
        setX(x);
        setY(y);
        
 
    }
        



	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}
		
	
}