package utilities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enum representing various payment methods.
 */
@Getter
@AllArgsConstructor
public enum PaymentMethod {
    CREDIT("Tarjeta de Crédito"),
    DEBIT("Tarjeta de Débito"),
    CASH("Dinero en efectivo");

    private final String method;

    /**
     * Get an array of all payment methods.
     *
     * @return Array of payment methods
     */
    public static String[] getAllPaymentMethods() {
        String[] paymentMethods = new String[values().length];
        for (int i = 0; i < values().length; i++) {
            paymentMethods[i] = values()[i].getMethod();
        }
        return paymentMethods;
    }
}
