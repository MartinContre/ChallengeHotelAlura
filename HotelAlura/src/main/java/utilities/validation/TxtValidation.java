package utilities.validation;

import utilities.JOptionPane.UserShowMessages;
import utilities.StringUtilities;
import utilities.enums.EmployeeCategory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class for validating different types of text input.
 * This class provides methods to validate employee categories, names or surnames,
 * and phone numbers, along with formatting phone numbers.
 * Usage:
 * - Use the static methods in this class to validate and format different types of text inputs.
 * Example:
 * To validate an employee category, use the {@link #validateEmployeeCategory(String)} method:
 * boolean isValidCategory = TxtValidation.validateEmployeeCategory("Receptionist");
 * To validate a name or surname, use the {@link #validateNameOrSurname(String)} method:
 * Matcher 'matcher' = TxtValidation.validateNameOrSurname("John Doe");
 * To validate and format a phone number, use the {@link #getValidPhoneNumber(String)} method:
 * String validPhoneNumber = TxtValidation.getValidPhoneNumber("55-43-22-22-43");
 */
public class TxtValidation {

    private TxtValidation() {

    }

    /**
     * Validates if the provided employee category is valid.
     *
     * @param search The employee category to validate.
     * @return {@code true} if the employee category is valid, {@code false} otherwise.
     */
    public static Boolean validateEmployeeCategory(String search) {

        EmployeeCategory employeeCategory = StringUtilities.convertUserCategoryStrToEmployeeCategory(search);

        return employeeCategory.equals(EmployeeCategory.Receptionist) || employeeCategory.equals(EmployeeCategory.Manager);
    }

    /**
     * Validates if the provided name or surname is valid based on a regex pattern.
     *
     * @param name The name or surname to validate.
     * @return A {@link Matcher} containing the result of the validation.
     */
    public static Matcher validateNameOrSurname(String name) {
        String regexName = "^(?=.{3,25}$)([A-ZÁÉÍÓÚ][a-záéíóúñ]+(?:\\s[A-ZÁÉÍÓÚ][a-záéíóúñ]+)*)$";
        Pattern patternName = Pattern.compile(regexName);

        return patternName.matcher(name);
    }

    /**
     * Validates if the provided phone number is valid based on a regex pattern.
     *
     * @param phone The phone number to validate.
     * @return A {@link Matcher} containing the result of the validation.
     */
    public static Matcher validatePhoneNumber(String phone) {
        String regexTel = "^(\\d{2}-){4}\\d{2}$";
        Pattern patternPhone = Pattern.compile(regexTel);

        return patternPhone.matcher(phone);
    }

    /**
     * Validates and formats a provided phone number.
     *
     * @param strPhone The phone number to validate and format.
     * @return The formatted phone number if valid.
     * @throws IllegalArgumentException If the phone number is invalid.
     * @see #validatePhoneNumber(String)
     */
    public static String getValidPhoneNumber(String strPhone) {
        if (!validatePhoneNumber(strPhone).find()) {
            UserShowMessages.showErrorMessage(
                    "Invalid phone.",
                    """
                            The supported format must contain 10 digits,\s
                             including the phone number of the state, separated by dashes(-):
                            55-43-22-22-43"""
            );
            throw new IllegalArgumentException("Invalid phone");
        } else {
            return strPhone;
        }
    }
}
