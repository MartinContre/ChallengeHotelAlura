package utilities;

import com.toedter.calendar.JDateChooser;
import model.User;
import utilities.JOptionPane.ErrorMessages;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormValidationUtility {
    public static Boolean isUserCorrect(User user, String userName, JPasswordField passwordField) {
        if (!userName.equals(user.getName())) {
            ErrorMessages.showErrorMessage(
                    "Incorrect user",
                    "The username entered is incorrect."
            );
            throw new IllegalArgumentException("Incorrect user");
        } else if (String.valueOf(passwordField.getPassword()).equals(user.getPassword())) {
            ErrorMessages.showErrorMessage(
                    "Incorrect password",
                    "The password entered is incorrect."
            );
            throw new IllegalArgumentException("Incorrect password");
        } else {
            return true;
        }
    }
    public static Boolean isBookingFormValid(
            JDateChooser checkIn, JDateChooser checkOut,
            String value,
            JComboBox<String> paymentMethod
    ) {
        if (checkIn.getDate() == null && checkOut.getDate() == null) {
            ErrorMessages.showErrorMessage(
                    "Invalid dates",
                    "Please select check in and check out please.\n" +
                            "You can write the date if follows the next format:\n" +
                            "dd/mm/yyyy"
            );
            throw new IllegalArgumentException("Dates not valid");
        } else if (value.equals("0.0")) {
            ErrorMessages.showErrorMessage(
                    "Value not valid",
                    "Please select check in and check out."
            );
            throw new IllegalArgumentException("Value not valid");
        }
        return true;
    }

    public static Boolean isGuestFormValid(
            String name, String surname, JDateChooser birthdate, String phone
    ) {
        String regexName = "^(?=.{3,25}$)([A-ZÁÉÍÓÚ][a-záéíóúñ]+(?:[\\s]{1}[A-ZÁÉÍÓÚ][a-záéíóúñ]+)*)$";
        String regexTel = "^([\\d]{2}[\\-]){4}[\\d]{2}$";
        Pattern patternName = Pattern.compile(regexName);
        Pattern patternPhone = Pattern.compile(regexTel);
        Matcher matchName = patternName.matcher(name);
        Matcher matchSurname = patternName.matcher(surname);
        Matcher matchPhone = patternPhone.matcher(phone);
        if (!matchName.find()) {
            ErrorMessages.showErrorMessage(
                    "Invalid Name",
                    "1. The name must contain the first capital letter: John\n"
                            + "2. Likewise if it is a compound name: John Doe\n"
                            + "3. If it is a single name, check that there are no blank spaces before or after."
            );
            throw new IllegalArgumentException("Invalid name");
        } else if (!matchSurname.find()) {
            ErrorMessages.showErrorMessage(
                    "Invalid surname.",
                    "1. The last name must contain the first capital letter: Kings\n"
                            + "2. Likewise if it is a compound last name: Reyes Hernandez\n"
                            + "3. If it is a single last name, check that there are no blank spaces before or after."
                    );
            throw new IllegalArgumentException("Invalid surname");
        } else if (birthdate.getDate() == null) {
            ErrorMessages.showErrorMessage(
                    "Invalid date.",
                    "Field date is empty.");
            throw new IllegalArgumentException("Invalid date");
        } else if (isOlder(birthdate.getDate())) {
            ErrorMessages.showErrorMessage(
                    "Invalid birthdate",
                    "Is underage.");
            throw new IllegalArgumentException("Invalid birthdate");
        } else if (!matchPhone.find()) {
            ErrorMessages.showErrorMessage(
                    "Invalid phone.",
                    "The supported format must contain 10 digits, \n " +
                            "including the phone number of the state, separated by dashes(-):\n"
                            + "55-43-22-22-43"
            );
            throw new IllegalArgumentException("Invalid phone");
        }
        return true;
    }

    public static Boolean isUserFormValid(
            String userName, JComboBox<String> userCategory,
            JPasswordField passwordField
    ) {
        if (userName.isEmpty()) {
            ErrorMessages.showErrorMessage(
                    "User name not valid",
                    "User field must not be null"
            );
            throw new IllegalArgumentException("User name not valid");
        } else if (userCategory.getSelectedIndex() == 0) {
            ErrorMessages.showErrorMessage(
                    "User category not valid.",
                    "Please select an user category"
            );
            throw new IllegalArgumentException("User category not valid");
        } else if (passwordField.getPassword().length == 0) {
            ErrorMessages.showErrorMessage("Password not valid.",
                    "Please fill password field.");
            throw new IllegalArgumentException("Password not valid");
        } else if (passwordField.getPassword().length > 30) {
            ErrorMessages.showErrorMessage("Invalid password..",
                    "Password too long, maximum 30 characters.");
            throw new IllegalArgumentException("Invalid password");
        }
        return true;
    }

    public static void onlyNumbers(KeyEvent keyEvent) {
        char c = keyEvent.getKeyChar();
        if (!Character.isDigit(c) && c != '.' && c != '-') {
            keyEvent.consume();
        }
    }

    private static Boolean isOlder(Date birthDate) {
        LocalDate birthdate = DateConvertor.convertDateToLocalDate(birthDate);
        LocalDate today = LocalDate.now();
        long yearDifference = ChronoUnit.YEARS.between(birthdate, today);
        return yearDifference < 18;
    }
}
