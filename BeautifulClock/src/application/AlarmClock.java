package application;
import java.util.Scanner;

/**
 * An alarm clock representation that extends the {@link Clock} class and includes alarm functionality.
 * 
 * @author Charaf idrissi
 */
public class AlarmClock extends Clock {
    private String alarmTime; // The time when the alarm is set
    private String snooze;
    private int snoozeMin;
    private final int ACCEPTABLE_SNOOZE_TIME = 11;
  
    Scanner keyboard = new Scanner(System.in);
   
    /**
     * Constructs a new {@code AlarmClock} object with the specified hours and minutes for the alarm.
     *
     * @param hours The hours for the alarm.
     * @param minutes The minutes for the alarm.
     */
    public AlarmClock(String hours, String minutes) {
        this.hours = hours;
        this.minutes = minutes;
    }
    
    /**
     * Retrieves the hours of the alarm.
     *
     * @return The hours of the alarm as a string.
     */
    public String getHours() {
        return hours;
    }
    
    /**
     * Retrieves the minutes of the alarm.
     *
     * @return The minutes of the alarm as a string.
     */
    public String getMinutes() {
        return minutes;
    }
    
    
    /**
     * Sets the alarm time by combining the hours and minutes.
     */
    public void setAlarm() {
        alarmTime = getHours() + "h:" + getMinutes() + "min";
    }
    
    /**
     * Retrieves the alarm time.
     * This method also sets the alarm before returning the time.
     *
     * @return The alarm time as a formatted string.
     */
    @Override
    public String getTime() {
        setAlarm();
        return alarmTime;
    }
    
    /**
     * Prints the alarm time and current time and determines if it's time to wake up.
     * If the alarm time matches the current time, it prints a wake-up message.
     * Otherwise, it prints a message indicating a desire for more sleep.
     */
    public void wakeUp() {
        System.out.println("Alarm time: " + getTime());
        System.out.println("Time now: " + super.getTime());

        if (getTime().equals(super.getTime())) {
            System.out.println("Wake up, you lazy boy!! The sun is out.");
            snooze();
        } else {
            System.out.println("Let me sleep a little longer...");
        }
    }
    
    
    /**
     * Retrieves the snooze value.
     * 
     * @return The snooze value as a string.
     */
    public String getSnooze() {
        return snooze;
    }
    
    /**
     * Sets the snooze value.
     * 
     * @param snooze The snooze value to be set.
     */
    public void setSnooze(String snooze) {
        this.snooze = snooze;
    }
    
    /**
     * Retrieves the snooze minutes.
     * 
     * @return The snooze minutes as an integer.
     */
    public int getSnoozeMin() {
        return snoozeMin;
    }
    
    /**
     * Sets the snooze minutes.
     * 
     * @param snoozeMin The snooze minutes to be set.
     */
    public void setSnoozeMin(int snoozeMin) {
        this.snoozeMin = snoozeMin;
    }
    
    
    /**
     * Adds a snooze time of specified minutes to the current alarm minutes.
     *
     * @param snoozeMin The snooze minutes to be added.
     */
    public void addSnoozeTime(int snoozeMin) {
        int intMinutes = Integer.parseInt(minutes);
        minutes = String.valueOf(intMinutes + snoozeMin);
    }
    
    /**
     * Allows the user to snooze the alarm.
     * Prompts the user for input and adds the specified number of snooze minutes.
     * Prints the updated alarm time if the snooze time is acceptable, otherwise prints a wake-up message.
     */
    public void snooze() {
        System.out.print("You want to add some minutes? Y/N ");
        String snooze = keyboard.next();
        setSnooze(snooze);
        
        if (snooze.equals("Y")) {
            System.out.print("How many? ");
            int snoozeMin = keyboard.nextInt();
            setSnoozeMin(snoozeMin);
            
            if (snoozeMin < ACCEPTABLE_SNOOZE_TIME) {
                addSnoozeTime(snoozeMin);
                correctAlarm();
                System.out.println("Alarm is now set to: " + getTime());
            } else {
                System.out.println("WAKE UUUUUUUUUUUP!");
            }
        }
    }
    public void correctAlarm() {
    	int intMins = Integer.parseInt(getMinutes());
    	int intHours = Integer.parseInt(getHours());
    	if (intMins > 60) {
    		minutes = String.valueOf(intMins - 60);
    		hours = String.valueOf(Integer.parseInt(hours) + 1);
    	}
    	if (intHours > 24) {
    		hours = String.valueOf(00);
    	}
    	
}
}
