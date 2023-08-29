package utilities.validation;

import com.toedter.calendar.JDateChooser;
import utilities.DateConvertor;
import utilities.JOptionPane.UserShowMessages;

import javax.swing.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.regex.Matcher;

public class FormValidationUtility {
    public static Boolean isBookingFormValid(
            JDateChooser checkIn, JDateChooser checkOut,
            String value
    ) {
        if (checkIn.getDate() == null && checkOut.getDate() == null) {
            UserShowMessages.showErrorMessage(
                    "Invalid dates",
                    """
                            Please select check in and check out please.
                            You can write the date if follows the next format:
                            dd/mm/yyyy"""
            );
            throw new IllegalArgumentException("Dates not valid");
        } else if (value.equals("0.0")) {
            UserShowMessages.showErrorMessage(
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
        Matcher matchName = TxtValidation.validateNameOrSurname(name);
        Matcher matchSurname = TxtValidation.validateNameOrSurname(surname);


        Matcher matchPhone = TxtValidation.validatePhoneNumber(phone);

        if (!matchName.find()) {
            UserShowMessages.showErrorMessage(
                    "Invalid Name",
                    """
                            1. The name must contain the first capital letter: John
                            2. Likewise if it is a compound name: John Doe
                            3. If it is a single name, check that there are no blank spaces before or after."""
            );
            throw new IllegalArgumentException("Invalid name");
        } else if (!matchSurname.find()) {
            UserShowMessages.showErrorMessage(
                    "Invalid surname.",
                    """
                            1. The last name must contain the first capital letter: Kings
                            2. Likewise if it is a compound last name: Reyes Hernandez
                            3. If it is a single last name, check that there are no blank spaces before or after."""
                    );
            throw new IllegalArgumentException("Invalid surname");
        } else if (birthdate.getDate() == null) {
            UserShowMessages.showErrorMessage(
                    "Invalid date.",
                    "Field date is empty.");
            throw new IllegalArgumentException("Invalid date");
        } else if (isOlder(birthdate.getDate())) {
            UserShowMessages.showErrorMessage(
                    "Invalid birthdate",
                    "Is underage.");
            throw new IllegalArgumentException("Invalid birthdate");
        } else if (!matchPhone.find()) {
            UserShowMessages.showErrorMessage(
                    "Invalid phone.",
                    """
                            The supported format must contain 10 digits,\s
                             including the phone number of the state, separated by dashes(-):
                            55-43-22-22-43"""
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
            UserShowMessages.showErrorMessage(
                    "User name not valid",
                    "User field must not be null"
            );
            throw new IllegalArgumentException("User name not valid");
        } else if (passwordField.getPassword().length == 0) {
            UserShowMessages.showErrorMessage(
                    "Password not valid.",
                    "Please fill password field."
            );
            throw new IllegalArgumentException("Password not valid");
        } else if (passwordField.getPassword().length > 30) {
            UserShowMessages.showErrorMessage(
                    "Invalid password..",
                    "Password too long, maximum 30 characters."
            );
            throw new IllegalArgumentException("Invalid password");
        } else if (userCategory.getSelectedIndex() == -1) {
            UserShowMessages.showErrorMessage(
                    "User category not valid.",
                    "Please select an user category"
            );
            throw new IllegalArgumentException("User category not valid");
        }
        return true;
    }


    public static boolean validateSearchField(JTextField searchField) {
        if (searchField.getText().isEmpty()) {
            UserShowMessages.showErrorMessage(
                    "Search field empty",
                    "Please fill search field"
            );
            throw new IllegalArgumentException("Search field empty");
        }
        return true;
    }

    private static Boolean isOlder(Date birthDate) {
        LocalDate birthdate = DateConvertor.convertDateToLocalDate(birthDate);
        LocalDate today = LocalDate.now();
        long yearDifference = ChronoUnit.YEARS.between(birthdate, today);
        return yearDifference < 18;
    }
}
