package utilities.validation;

import utilities.JOptionPane.UserShowMessages;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Utility class for validating and handling form input for booking, guest, and user data.
 * This class provides methods to validate form inputs for booking, guest, and user data.
 * It also includes methods for checking and formatting dates, ages, and phone numbers.
 */
public class SQLDataValidator {

    private static final String DATE_FORMAT = "yyyy-MM-dd";


    private SQLDataValidator() {

    }

    /**
     * Validates and converts a birthdate string to an SQL Date object.
     *
     * @param dateString The birthdate string in "yyyy-MM-dd" format.
     * @return The validated SQL Date object.
     * @throws IllegalArgumentException If the birthdate is invalid or indicates an underage individual.
     */
    public static Date isBirthdateValid(String dateString) {
        Date date = convertToDate(
                dateString
        );

        if (isOlder(date)) {
            UserShowMessages.showErrorMessage(
                    "Invalid date",
                    "Is Under Age");
            throw new IllegalArgumentException();
        }
        return date;
    }

    /**
     * Validates and converts a check-in date string to an SQL Date object.
     *
     * @param dateString The check-in date string in "yyyy-MM-dd" format.
     * @return The validated SQL Date object.
     * @throws IllegalArgumentException If the check-in date is before today.
     */
    public static Date isCheckInValid(String dateString) {
        Date currentDate = new Date(System.currentTimeMillis());
        Date date = convertToDate(
                dateString
        );

        if (date.before(currentDate)) {
            UserShowMessages.showErrorMessage(
                    "Invalid dates",
                    "Date check in is before today");
            throw new IllegalArgumentException();
        }

        return date;
    }

    /**
     * Validates and converts a check-out date string to an SQL Date object.
     *
     * @param dateString The check-out date string in "yyyy-MM-dd" format.
     * @param checkIn    The associated check-in date.
     * @return The validated SQL Date object.
     * @throws IllegalArgumentException If the check-out date is before the check-in date.
     */
    public static Date isCheckOutValid(String dateString, Date checkIn) {
        Date checkOut = convertToDate(
                dateString
        );

        if (checkOut.before(checkIn)) {
            UserShowMessages.showErrorMessage(
                    "Invalid dates",
                    "Date check out is before check in");
            throw new IllegalArgumentException();
        }

        return checkOut;
    }


    /**
     * Converts a date string to an SQL Date object.
     *
     * @param dateString The date string in "yyyy-MM-dd" format.
     * @return The converted SQL Date object.
     * @throws IllegalArgumentException If the date string is invalid.
     */
    private static Date convertToDate(String dateString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
            dateFormat.setLenient(false);
            java.util.Date parsedDate = dateFormat.parse(dateString);
            return new Date(parsedDate.getTime());
        } catch (ParseException e) {
            UserShowMessages.showErrorMessage(
                    "Formatted day invalid",
                    "You need to introduce a date in the format yyyy-mm-dd"
            );
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    /**
     * Checks if a given birthdate indicates an individual younger than 18 years old.
     *
     * @param birthDate The birthdate to be checked.
     * @return True if the individual is younger than 18, false otherwise.
     */
    private static Boolean isOlder(Date birthDate) {
        LocalDate birthdate = birthDate.toLocalDate();
        LocalDate today = LocalDate.now();
        long yearDifference = ChronoUnit.YEARS.between(birthdate, today);
        return yearDifference < 18;
    }

}