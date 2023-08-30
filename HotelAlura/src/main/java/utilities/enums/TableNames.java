package utilities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * Enum representing table names in the database.
 */
@Getter
@AllArgsConstructor
public enum TableNames {
    GUESTS("guests"),
    USERS("users"),
    BOOKINGS("bookings");

    private final String key;
}
