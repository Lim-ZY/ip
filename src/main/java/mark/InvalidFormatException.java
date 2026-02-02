package mark;

/**
 * Represents an invalid format exception with special exception message.
 */
public class InvalidFormatException extends Exception {
    public InvalidFormatException(String message) {
        super("Invalid format! " + message);
    }
}
