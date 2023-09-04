package application;
import java.time.ZoneId;

/**
 * A world clock representation that extends the {@link Clock} class and incorporates an offset for different time zones.
 * @author: Charaf Idrissi
 */
public class WorldClock extends Clock {
    int offset; // The time zone offset

    /**
     * Constructs a new {@code WorldClock} object with the specified offset.
     *
     * @param offset The time zone offset in hours.
     */
    WorldClock(int offset) {
        this.offset = offset;
    }

    /**
     * Retrieves the time zone offset.
     *
     * @return The time zone offset in hours.
     */
    public int getOffset() {
        return this.offset;
    }

    /**
     * Retrieves the current hour adjusted for the time zone offset.
     *
     * @return The adjusted hour as a string.
     */
    public String getHours() {
    	ZoneId utcZone = ZoneId.of("UTC");
    	String utcHours = java.time.LocalTime.now(utcZone).toString().substring(0, 2);
        int hourIntegerOffset = Integer.parseInt(utcHours) + getOffset();
        if (hourIntegerOffset >= 24) {
            hourIntegerOffset = hourIntegerOffset - 24;
        }
        if (hourIntegerOffset < 0) {
            hourIntegerOffset = hourIntegerOffset + 24;
        }
        String hourStringOffset = String.valueOf(hourIntegerOffset);

        return hourStringOffset;
    }

    /**
     * Retrieves the formatted time string including the adjusted hour and minutes.
     *
     * @return The adjusted time as a formatted string (e.g., "hh:hmin").
     */
    @Override
    public String getTime() {
        return getHours() + "h:" + getMinutes() + "min";
    }
}