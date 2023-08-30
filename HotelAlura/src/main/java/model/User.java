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

/**
 * Represents a user in the system.
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {
    /**
     * The unique identifier for the user.
     */
    private Integer id;
    /**
     * The name of the user.
     */
    private String name;
    /**
     * The category of the user (e.g., Manager).
     */
    private EmployeeCategory category;
    /**
     * The hashed password of the user.
     */
    private String password;

    /**
     * Constructs a User object with the specified name and category.
     *
     * @param name     The name of the user.
     * @param category The category of the user.
     */
    public User(String name, EmployeeCategory category) {
        this.name = name;
        this.category = category;
    }

    /**
     * Sets the password for the user by hashing it.
     *
     * @param password The password to set.
     */
    public void setPassword(String password) {
        this.password = hashPassword(password);
    }

    /**
     * Hashes the provided password using the SHA-256 algorithm.
     *
     * @param password The password to hash.
     * @return The hashed password.
     */
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
