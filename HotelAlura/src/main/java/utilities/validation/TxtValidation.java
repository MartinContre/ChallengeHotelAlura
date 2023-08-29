package utilities.validation;

import utilities.JOptionPane.UserShowMessages;
import utilities.StringUtilities;
import utilities.enums.EmployeeCategory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TxtValidation {

    private TxtValidation() {

    }

    public static Boolean validateEmployeeCategory(String search) {

        EmployeeCategory employeeCategory = StringUtilities.convertUserCategoryStrToEmployeeCategory(search);

        return employeeCategory.equals(EmployeeCategory.Receptionist) || employeeCategory.equals(EmployeeCategory.Manager);
    }

    public static Matcher validateNameOrSurname(String name) {
        String regexName = "^(?=.{3,25}$)([A-ZÁÉÍÓÚ][a-záéíóúñ]+(?:\\s[A-ZÁÉÍÓÚ][a-záéíóúñ]+)*)$";
        Pattern patternName = Pattern.compile(regexName);

        return patternName.matcher(name);
    }


    public static Matcher validatePhoneNumber(String phone) {
        String regexTel = "^(\\d{2}-){4}\\d{2}$";
        Pattern patternPhone = Pattern.compile(regexTel);

        return patternPhone.matcher(phone);
    }

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
