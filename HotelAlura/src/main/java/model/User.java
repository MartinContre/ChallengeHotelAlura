package model;

import lombok.*;
import utilities.enums.EmployeeCategory;

@Data
@ToString(exclude = "password")
@AllArgsConstructor
public class User {
    private Integer id;
    private String name;
    private String category;
    private String password;

    public User(Integer id, String name, String category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    public User(String name, String category) {
        this.name = name;
        this.category = category;
    }

    public User(String name, EmployeeCategory category, String password) {
        this.name = name;
        this.category = category.toString();
        this.password = password;
    }
}
