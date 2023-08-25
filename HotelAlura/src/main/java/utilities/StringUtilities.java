package utilities;

import utilities.JOptionPane.UserShowMessages;
import utilities.enums.EmployeeCategory;
import utilities.enums.PaymentMethod;

public class StringUtilities {
    private StringUtilities() {

    }

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
