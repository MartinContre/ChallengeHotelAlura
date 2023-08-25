package model;

import lombok.*;
import utilities.enums.EmployeeCategory;

@Data
@ToString(exclude = "password")
@AllArgsConstructor
public class User {
    private Integer id;
    private String name;
    private EmployeeCategory category;
    private String password;

    public User(Integer id, String name, EmployeeCategory category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    public User(String name, EmployeeCategory category) {
        this.name = name;
        this.category = category;
    }

    public User(String name, EmployeeCategory category, String password) {
        this.name = name;
        this.category = category;
        this.password = password;
    }
}
