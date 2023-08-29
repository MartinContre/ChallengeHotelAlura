package utilities;

import utilities.json.JsonReader;
import utilities.json.JsonReaderUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class CalculateValue {
    private static final JsonReader CONFIG_DATA = JsonReaderUtils.getConfigDataFile();

    private static final BigDecimal pricePerNight = new BigDecimal(CONFIG_DATA.getValue("PRICE"));

    private CalculateValue() {

    }

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

    public static BigDecimal getBookingValue(Long days) {
        BigDecimal totalPrice;


        BigDecimal bookingValue = pricePerNight.multiply(BigDecimal.valueOf(days));
        totalPrice = bookingValue.setScale(2, RoundingMode.HALF_UP).stripTrailingZeros();

        return totalPrice;
    }
}
