package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.sql.Date;

/**
 * Represents a guest in the system.
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Guest {
    /**
     * The unique identifier for the guest.
     */
    private Integer id;
    /**
     * The first name of the guest.
     */
    private String name;
    /**
     * The last name of the guest.
     */
    private String surname;
    /**
     * The birthdate of the guest.
     */
    private Date birthdate;
    /**
     * The nationality of the guest.
     */
    private String nationality;
    /**
     * The phone number of the guest.
     */
    private String phone;
    /**
     * The booking ID associated with the guest.
     */
    private String bookingId;

    /**
     * Constructs a Guest object with the specified name, surname, birthdate, nationality, and phone.
     *
     * @param name        The first name of the guest.
     * @param surname     The last name of the guest.
     * @param birthdate   The birthdate of the guest.
     * @param nationality The nationality of the guest.
     * @param phone       The phone number of the guest.
     */
    public Guest(String name, String surname, Date birthdate, String nationality, String phone) {
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        this.nationality = nationality;
        this.phone = phone;
    }

}
