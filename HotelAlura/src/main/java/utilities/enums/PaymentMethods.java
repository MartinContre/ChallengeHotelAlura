package utilities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentMethods {
    CREDIT("Tarjeta de Crédito"),
    DEBIT("Tarjeta de Débito"),
    CASH("Dinero en efectivo");

    private final String method;

    public static String[] getAllPaymentMethods() {
        String[] paymentMethods = new String[values().length];
        for (int i = 0; i < values().length; i++) {
            paymentMethods[i] = values()[i].getMethod();
        }
        return paymentMethods;
    }
}
