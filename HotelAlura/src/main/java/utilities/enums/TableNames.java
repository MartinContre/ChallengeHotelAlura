package utilities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TableNames {
    GUESTS("guests"),
    USERS("users"),
    BOOKINGS("bookings");

    private final String key;
}
