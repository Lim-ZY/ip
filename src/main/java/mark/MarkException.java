package mark;

/**
 * Abstracts exceptions specific to the Mark application.
 */
public class MarkException extends Exception {
    /**
     * Returns a MarkException with the specified error message.
     */
    public MarkException(String message) {
        super(message);
    }
}
