package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import utilities.enums.EmployeeCategory;
import utilities.exception.PasswordHashingException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    private String name;
    private EmployeeCategory category;
    private String password;

    public User(String name, EmployeeCategory category) {
        this.name = name;
        this.category = category;
    }


    public void setPassword(String password) {
        this.password = hashPassword(password);
    }

    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));

            StringBuilder hashStringBuilder = new StringBuilder();
            for (byte b : hashBytes) {
                hashStringBuilder.append(String.format("%02x", b));
            }
            return hashStringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new PasswordHashingException("Error while hashing password: " + e.getMessage());
        }
    }

}
