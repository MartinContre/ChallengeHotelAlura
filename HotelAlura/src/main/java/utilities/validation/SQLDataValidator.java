package utilities.validation;

import utilities.JOptionPane.UserShowMessages;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class SQLDataValidator {

    private static final String DATE_FORMAT = "yyyy-MM-dd";


    private SQLDataValidator() {

    }

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

    private static Boolean isOlder(Date birthDate) {
        LocalDate birthdate = birthDate.toLocalDate();
        LocalDate today = LocalDate.now();
        long yearDifference = ChronoUnit.YEARS.between(birthdate, today);
        return yearDifference < 18;
    }

}