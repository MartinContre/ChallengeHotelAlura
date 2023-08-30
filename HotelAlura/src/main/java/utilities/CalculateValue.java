package utilities;

import utilities.json.JsonReader;
import utilities.json.JsonReaderUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Utility class for calculating booking values based on check-in and check-out dates.
 * This class provides methods to calculate the total booking value using predefined price per night.
 * The price per night is obtained from a JSON configuration file using {@link JsonReader} and {@link JsonReaderUtils}.
 * Usage:
 * - Use the static methods in this class to calculate the booking value based on check-in and check-out dates or days.
 * - The calculated values are rounded and formatted to two decimal places.
 * Example:
 * To calculate the booking value based on check-in and check-out dates, use the getBookingValue method as follows:
 * Date checkInDate = ...; // Set the check-in date.
 * Date checkOutDate = ...; // Set the check-out date.
 * BigDecimal bookingValue = CalculateValue.getBookingValue(checkInDate, checkOutDate);
 * To calculate the booking value based on the number of days, use the getBookingValue method as follows:
 * long numberOfDays = ...; // Set the number of days
 * BigDecimal bookingValue = CalculateValue.getBookingValue(numberOfDays);
 * Note: The pricing details and configuration are obtained from the JSON configuration file.
 * Ensure that the pricing data is properly configured in the configuration file.
 */
public class CalculateValue {
    private static final JsonReader CONFIG_DATA = JsonReaderUtils.getConfigDataFile();

    private static final BigDecimal pricePerNight = new BigDecimal(CONFIG_DATA.getValue("PRICE"));

    private CalculateValue() {

    }

    /**
     * Calculates the booking value based on the check-in and check-out dates.
     *
     * @param checkIn  The check-in date.
     * @param checkOut The check-out date.
     * @return The calculated booking value as a {@link BigDecimal}, rounded to two decimal places.
     * Returns {@code null} if the check-in date is after the check-out date.
     */
    public static BigDecimal getBookingValue(Date checkIn, Date checkOut) {
        BigDecimal totalPrice = null;

        LocalDate checkInLD = checkIn.toLocalDate();
        LocalDate checkOutLD = checkOut.toLocalDate();


        long days = ChronoUnit.DAYS.between(checkInLD, checkOutLD);

        if (days > 0) {
            BigDecimal bookingValue = pricePerNight.multiply(BigDecimal.valueOf(days));
            totalPrice = bookingValue.setScale(2, RoundingMode.HALF_UP);
        }

        return totalPrice;
    }

    /**
     * Calculates the booking value based on the number of days.
     *
     * @param days The number of days.
     * @return The calculated booking value as a {@link BigDecimal}, rounded to two decimal places and without trailing zeros.
     */
    public static BigDecimal getBookingValue(Long days) {
        BigDecimal totalPrice;


        BigDecimal bookingValue = pricePerNight.multiply(BigDecimal.valueOf(days));
        totalPrice = bookingValue.setScale(2, RoundingMode.HALF_UP).stripTrailingZeros();

        return totalPrice;
    }
}
