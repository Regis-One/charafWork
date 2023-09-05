package application;

import javax.print.attribute.standard.Media;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
// Import necessary JavaFX libraries
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;



public class Main extends Application {
    public static void main(String[] args) {
        // Launch the JavaFX application
        launch(args);
       
        
    }

	/*
	 * // Helper method to create and configure Text nodes for numbers private Text
	 * createNumberText(String text, double x, double y) { Text numberText = new
	 * Text(text); numberText.setFont(Font.font("Arial", FontWeight.BOLD, 20));
	 * numberText.setFill(Color.WHITE); numberText.setX(x); numberText.setY(y);
	 * return numberText; } private double getClockNumbersAngle(Text numberClock) {
	 * double numberClockAngle = 0; if (numberClock.getText() == "12") {
	 * numberClockAngle = 180; } return numberClockAngle; }
	 */
    @Override
    public void start(Stage stage) {
    	
        // Set the title of the application window
        stage.setTitle("Clock");

        // Create a root pane to hold the clock elements
        Pane root = new Pane();

        // Set the background color of the root pane to black
        root.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        // Load an image for the application icon (change the file path to your image)
        Image icon = new Image("file:///C:/Users/16132/OneDrive%20-%20Algonquin%20College/Documents/Heavy%20Programming/Java/Level3%20Workspace/BeautifulClock/walmart.png");

        // Create a scene with a specified size and black background
        Scene scene = new Scene(root, 480, 480);

        // Set the application icon
        stage.getIcons().add(icon);

        
      
        WorldClock clock = new WorldClock(-4);
        int dotIndex = clock.getClass().getName().indexOf(".");
        String clockName = clock.getClass().getName().substring(dotIndex+1);
        Label watchLabel = new Label(clockName + " offset by: " + clock.getOffset() +" hours");
        watchLabel.setStyle("-fx-text-fill: white;");
        watchLabel.setTranslateY(420);
        watchLabel.setTranslateX(240);
       
        // Calculate the center coordinates of the scene
        double centerX = scene.getWidth() / 2;
        double centerY = scene.getHeight() / 2;

        // Create a rectangle for the seconds hand
        Rectangle secondsHand = new Rectangle(1, 100);
        secondsHand.setStrokeWidth(2);
        secondsHand.setStroke(Color.BLANCHEDALMOND);

        // Translate the seconds hand to its initial position
        Translate translateSeconds = new Translate(centerX - 30, centerY - 50);
        secondsHand.getTransforms().add(translateSeconds);

        // Rotate the seconds hand by 180 degrees
        Rotate rotateSeconds = new Rotate();
        rotateSeconds.setAngle(180);
        secondsHand.getTransforms().add(rotateSeconds);

        // Create rectangles for the hours and minutes hands
        // Similar translations and rotations are applied to them

	     Rectangle hoursHand = new Rectangle(3, 50);
        hoursHand.setStrokeWidth(3);
        hoursHand.setStroke(Color.GREEN);
        
        Translate translateHours = new Translate(centerX - 30, centerY - 50); 
        hoursHand.getTransforms().add(translateHours);
        
        
        Rotate rotateHours = new Rotate();
        rotateHours.setAngle(180+((Integer.parseInt(clock.getHours()) % 12) +  (Integer.parseInt(clock.getMinutes()) / 60.0)) * 30);  
        System.out.println(rotateHours.getAngle());
        hoursHand.getTransforms().add(rotateHours);
        
        Rectangle minutesHand = new Rectangle(2, 70);
        minutesHand.setStrokeWidth(2);
        minutesHand.setStroke(Color.BLUE);
        
        Translate translateMinutes = new Translate(centerX - 30, centerY - 50); 
        minutesHand.getTransforms().add(translateMinutes);
        
        Rotate rotateMinutes = new Rotate();
        rotateMinutes.setAngle(180+(Integer.parseInt(clock.getMinutes()) * 6));        
        minutesHand.getTransforms().add(rotateMinutes);

        // Create Text nodes for the clock numbers
        createNumberTextClock one = new createNumberTextClock("1", 260, 100, 210);
        createNumberTextClock two = new createNumberTextClock("2", 300, 140, 240);
        createNumberTextClock three = new createNumberTextClock("3", 310, 200, 270);
        createNumberTextClock four = new createNumberTextClock("4", 300, 260, 300);
        createNumberTextClock five = new createNumberTextClock("5", 260, 298, 330);
        createNumberTextClock six = new createNumberTextClock("6", 200, 310, 0);
        createNumberTextClock seven = new createNumberTextClock("7", 144, 297, 30);
        createNumberTextClock eight = new createNumberTextClock("8", 110, 260, 60);
        createNumberTextClock nine = new createNumberTextClock("9", 90, 200, 90);
        createNumberTextClock ten = new createNumberTextClock("10", 97, 140, 120);
        createNumberTextClock eleven = new createNumberTextClock("11", 130, 100, 150);
        createNumberTextClock twelve = new createNumberTextClock("12", 200, 81, 180);

        // Create a circle to represent the clock face
        Circle watch = new Circle(centerX - 30, centerY - 50, 125);
        watch.setFill(Color.TRANSPARENT);
        watch.setStrokeWidth(3);
        watch.setStroke(Color.RED);

  
        
        

        // Add all clock elements to the root pane
        root.getChildren().addAll(watchLabel, hoursHand, minutesHand, secondsHand, watch, one, two, three, four, five, six, seven, eight, nine, ten, eleven, twelve);

       
        // Set the scene, configure the stage, and display it
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        // Create a timeline for the clock animation
        Timeline timeline = new Timeline(
        	    new KeyFrame(Duration.seconds(1), event -> {
        	        // Animation updates (rotate clock hands)
        	        Rotate continousRotateSeconds = new Rotate();
        	        Rotate continousRotateMinutes = new Rotate();
        	        Rotate continousRotateHours = new Rotate();
        	        continousRotateSeconds.setAngle(3);
        	        secondsHand.getTransforms().add(continousRotateSeconds);
        	        continousRotateMinutes.setAngle(0.1);
        	        minutesHand.getTransforms().add(continousRotateMinutes);
        	        continousRotateHours.setAngle(0.0083);
        	        hoursHand.getTransforms().add(continousRotateHours);
        	        
        	        AudioPlayer.playAudio();
        	    })
        	    
        	);

        	// Set the timeline to run indefinitely
        	timeline.setCycleCount(Timeline.INDEFINITE);

        	// Start the timeline animation
        	timeline.play();

        
    }
    
}
