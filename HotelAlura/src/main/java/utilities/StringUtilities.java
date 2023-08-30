package utilities;

import utilities.JOptionPane.UserShowMessages;
import utilities.enums.EmployeeCategory;
import utilities.enums.PaymentMethod;

/**
 * Utility class for string-related operations.
 * This class provides methods to convert string representations into corresponding enum values,
 * and performs validation and error handling for certain cases.
 * Usage:
 * - Use the static methods in this class to perform string-related operations.
 * Example:
 * To convert a string representation of a user category to the corresponding EmployeeCategory enum value,
 * use the convertUserCategoryStrToEmployeeCategory method as follows:
 * String userCategoryStr = "Manager";
 * EmployeeCategory userCategory = StringUtilities.convertUserCategoryStrToEmployeeCategory(userCategoryStr);
 * Similarly, to convert a string representation of a payment method to the corresponding PaymentMethod enum value,
 * use the convertPaymentMethodStrToPaymentMethod method:
 * String paymentMethodStr = "Tarjeta de Crédito";
 * PaymentMethod 'paymentMethod' = StringUtilities.convertPaymentMethodStrToPaymentMethod(paymentMethodStr);
 */
public class StringUtilities {
    private StringUtilities() {

    }

    /**
     * Converts a string representation of user category to the corresponding EmployeeCategory enum value.
     *
     * @param userCategoryStr The string representation of user category.
     * @return The corresponding EmployeeCategory enum value.
     * @throws IllegalArgumentException if the user category string is not valid.
     */
    public static EmployeeCategory convertUserCategoryStrToEmployeeCategory(String userCategoryStr) {
        userCategoryStr = userCategoryStr.trim().toUpperCase().replaceAll("\\s", "");
        if (userCategoryStr.equals("MANAGER")) {
            return EmployeeCategory.Manager;
        } else if (userCategoryStr.equals("RECEPTIONIST")) {
            return EmployeeCategory.Receptionist;
        } else {
            UserShowMessages.showErrorMessage(
                    "Error in user category",
                    "Error typing user category, should be Manager or Receptionist"
            );
            throw new IllegalArgumentException();
        }
    }

    /**
     * Converts a string representation of payment method to the corresponding PaymentMethod enum value.
     *
     * @param paymentMethodStr The string representation of payment method.
     * @return The corresponding PaymentMethod enum value.
     * @throws IllegalArgumentException if the payment method string is not valid.
     */
    public static PaymentMethod convertPaymentMethodStrToPaymentMethod(String paymentMethodStr) {
        paymentMethodStr = paymentMethodStr.trim().toUpperCase();
        if (paymentMethodStr.equals(PaymentMethod.CREDIT.getMethod().toUpperCase())) {
            return PaymentMethod.CREDIT;
        } else if (paymentMethodStr.equals(PaymentMethod.DEBIT.getMethod().toUpperCase())) {
            return PaymentMethod.DEBIT;
        } else if (paymentMethodStr.equals(PaymentMethod.CASH.getMethod().toUpperCase())) {
            return PaymentMethod.CASH;
        } else {
            UserShowMessages.showErrorMessage(
                    "Error in payment method",
                    String.format("Error typing payment method, should be %s", (Object) PaymentMethod.getAllPaymentMethods())
            );
            throw new IllegalArgumentException();
        }
    }

}
