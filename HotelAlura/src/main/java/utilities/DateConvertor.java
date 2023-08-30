package utilities;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * Utility class for converting between Java's Date and Java 8's LocalDate.
 * This class provides methods to convert Date objects to LocalDate objects.
 * Usage:
 * - Use the static method in this class to convert a Date object to a LocalDate object.
 * Example:
 * To convert a Date object to a LocalDate object, use the convertDateToLocalDate method as follows:
 * Date 'date'= ...; // Set the Date object
 * LocalDate 'localDate' = DateConvertor.convertDateToLocalDate(date);
 * Note: The time zone used for the conversion is the system's default time zone.
 * Ensure that the time zone settings are consistent with accurate conversions.
 */
public class DateConvertor {
    private DateConvertor() {
    }

    /**
     * Converts a Date object to a LocalDate object.
     *
     * @param date The Date object to be converted.
     * @return The equivalent LocalDate object.
     */
    public static LocalDate convertDateToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
