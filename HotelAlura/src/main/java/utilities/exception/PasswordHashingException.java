package utilities.exception;

/**
 * Custom exception class for password hashing errors.
 */
public class PasswordHashingException extends RuntimeException {

    /**
     * Constructs a new PasswordHashingException with the specified detail message.
     *
     * @param message the detail message.
     */
    public PasswordHashingException(String message) {
        super(message);
    }

}
