package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Guest {
    private Integer id;
    private String name;
    private String surname;
    private Date birthdate;
    private String nationality;
    private String phone;
    private String bookingId;

    public Guest(String name, String surname, Date birthdate, String nationality, String phone) {
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        this.nationality = nationality;
        this.phone = phone;
    }
}
