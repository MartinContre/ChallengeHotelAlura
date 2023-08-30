package utilities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enum representing column names for database tables.
 */
@Getter
@AllArgsConstructor
public enum TablesColumns {
    ID("Id"),
    CHECK_IN("Fecha Check In"),
    CHECK_OUT("Fecha Check Out"),
    VALUE("Valor"),
    PAYMENT_METHOD("Metodo de pago"),
    GUEST_NUMBER("Numero de Huesped"),
    NAME("Name"),
    SURNAME("Apellido"),
    BIRTHDATE("Fecha de Nacimiento"),
    NATIONALITY("Nacionalidad"),
    PHONE("Telefono"),
    BOOKING_ID("Numero de Reserva"),
    USER_ID("User Id"),
    USER_NAME("User Name"),
    USER_CATEGORY("User Category"),
    USER_PASSWORD("User Password");

    private final String key;

}
