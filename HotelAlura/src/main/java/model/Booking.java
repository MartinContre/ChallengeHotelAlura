package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Booking {
    private String BookingId;
    private Date checkIn;
    private Date checkOut;
    private BigDecimal value;
    private String paymentMethod;

    public Booking(Date checkIn, Date checkOut, BigDecimal value, String paymentMethod) {
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.value = value;
        this.paymentMethod = paymentMethod;
    }
}
