package utilities;

import utilities.JOptionPane.UserShowMessages;
import utilities.enums.EmployeeCategory;
import utilities.enums.PaymentMethods;

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

    public static PaymentMethods  convertPaymentMethodStrToPaymentMethod(String paymentMethodStr) {
        paymentMethodStr = paymentMethodStr.trim().toUpperCase();
        if (paymentMethodStr.equals(PaymentMethods.CREDIT.getMethod().toUpperCase())) {
            return PaymentMethods.CREDIT;
        } else if (paymentMethodStr.equals(PaymentMethods.DEBIT.getMethod().toUpperCase())) {
            return PaymentMethods.DEBIT;
        } else if (paymentMethodStr.equals(PaymentMethods.CASH.getMethod().toUpperCase())) {
            return PaymentMethods.CASH;
        } else {
            UserShowMessages.showErrorMessage(
                    "Error in payment method",
                    String.format("Error typing payment method, should be %s", (Object) PaymentMethods.getAllPaymentMethods())
            );
            throw new IllegalArgumentException();
        }
    }

}
