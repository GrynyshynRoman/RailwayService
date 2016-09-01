package ua.nure.hrynyshyn.core.supportClasses;

import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * Provide simple methods for parsing date and time.
 */
public final class DateTimeSupport {
    private static final Logger log = Logger.getLogger(DateTimeSupport.class.getName());

    /**
     * Converts specified date to long value.
     *
     * @param date value to converting
     * @return date in long
     */
    public static long parseDate(String date) {
        long result = 0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+3"));
        try {
            result = dateFormat.parse(date).getTime();
        } catch (ParseException e) {
            log.error("String parsing failure", e);
        }
        return result;
    }

    /**
     * Converts specified time to long value. Works only with "HH:mm" time pattern.
     *
     * @param time value to converting
     * @return time in long
     */
    public static int parseTime(String time) {
        String hours = time.split(":")[0];
        String minutes = time.split(":")[1];
        try {
            return (Integer.parseInt(hours) * 60 + Integer.parseInt(minutes)) * 60 * 1000;
        } catch (NumberFormatException e) {
            log.error("String parsing failure", e);
        }
        return 0;


    }

    /**
     * Converts date with time to long value.
     *
     * @param dateTime value for parsing.
     * @return long value
     */
    public static long parseDateTime(String dateTime) {
        long result = 0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+3"));
        try {
            result = dateFormat.parse(dateTime).getTime();
        } catch (ParseException e) {
            log.error("String parsing failure", e);
        }
        return result;
    }

}
