package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import utilities.enums.PaymentMethod;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Booking {
    private Integer id;
    private String BookingId;
    private Date checkIn;
    private Date checkOut;
    private BigDecimal value;
    private PaymentMethod paymentMethod;

    public Booking(Date checkIn, Date checkOut, BigDecimal value, PaymentMethod paymentMethod) {
        this.BookingId = generateBookingId();
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.value = value;
        this.paymentMethod = paymentMethod;
    }

    public String generateBookingId() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
