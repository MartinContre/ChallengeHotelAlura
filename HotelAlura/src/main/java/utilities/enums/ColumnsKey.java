package utilities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ColumnsKey {
    ID("id"),
    USER_NAME("user_name"),
    USER_CATEGORY("user_category"),
    PASSWORD("password"),
    NAME("name"),
    SURNAME("surname"),
    BIRTHDATE("birthdate"),
    NATIONALITY("nationality"),
    PHONE("phone"),
    BOOKING_ID("booking_id"),
    CHECK_IN("check_in"),
    CHECK_OUT("check_out"),
    VALUE("value"),
    PAYMENT_METHOD("payment_method");

    private final String key;

}
