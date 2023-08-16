package exception;

/**
 * Exception thrown when a key is not found.
 */
public class KeyNotFoundException extends Exception {

    /**
     * Constructs a new KeynotFoundException with the specified detail message.
     *
     * @param message the detail message.
     */
    public KeyNotFoundException(String message) {
        super(message);
    }
}
