package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import utilities.enums.PaymentMethod;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

/**
 * Represents a booking made in the system.
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Booking {
    /**
     * The unique identifier for the booking.
     */
    private Integer id;

    /**
     * The unique booking ID generated for the booking.
     */
    private String bookingId;

    /**
     * The check-in date of the booking.
     */
    private Date checkIn;

    /**
     * The check-out date of the booking.
     */
    private Date checkOut;

    /**
     * The value or cost of the booking.
     */
    private BigDecimal value;

    /**
     * The payment method used for the booking.
     */
    private PaymentMethod paymentMethod;


    /**
     * Constructs a Booking object with the specified check-in, check-out, value, and payment method.
     *
     * @param checkIn       The check-in date.
     * @param checkOut      The check-out date.
     * @param value         The value or cost of the booking.
     * @param paymentMethod The payment method.
     */
    public Booking(Date checkIn, Date checkOut, BigDecimal value, PaymentMethod paymentMethod) {
        this.bookingId = generateBookingId();
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.value = value;
        this.paymentMethod = paymentMethod;
    }

    /**
     * Generates a unique booking ID using a UUID.
     *
     * @return The generated booking ID.
     */
    private String generateBookingId() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
