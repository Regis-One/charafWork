package application;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class AudioPlayer {

	    public static void playAudio() {
	        try {
	        	String musicFile = "clockSound.mp3";     // For example

	        	Media sound = new Media(new File(musicFile).toURI().toString());
	        	MediaPlayer mediaPlayer = new MediaPlayer(sound);
	        	mediaPlayer.setAutoPlay(true);  
	        	
	        	
	        	
	         
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
}
