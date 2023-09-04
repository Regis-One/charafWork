package application;
/**
 * A simple clock representation that displays the current time in hours and minutes.
 * @author: Charaf Idrissi
 */
public class Clock implements ClockWork {
    public String hours; // The current hour
    public String minutes; // The current minute
    private String time; // The formatted time string

    /**
     * Constructs a new Clock object and initializes the hours, minutes, and time fields.
     * The hours and minutes are extracted from the current system time.
     * The time field is formatted as "hh:hmin".
     */
    public Clock() {
        this.hours = java.time.LocalTime.now().toString().substring(0, 2);
        this.minutes = java.time.LocalTime.now().toString().substring(3, 5);
        this.time = getHours() + "h:" + getMinutes() + "min";
    }

    /**
     * Retrieves the current hour.
     *
     * @return The current hour as a string.
     */
    public String getHours() {
        return hours;
    }

    /**
     * Retrieves the current minute.
     *
     * @return The current minute as a string.
     */
    public String getMinutes() {
        return minutes;
    }

    /**
     * Retrieves the formatted time string.
     *
     * @return The time as a formatted string (e.g., "hh:hmin").
     */
    public String getTime() {
        return time;
    }
    

}
