package zenn.exceptions;

/**
 * Represents a custom exception for the Zenn application.
 * This exception is thrown when a specific error occurs within the application.
 */
public class ZennException extends Exception {

    /**
     * Constructs a new ZennException with the specified detail message.
     *
     * @param exception The detail message, which provides additional information about the error.
     */
    public ZennException(String exception) {
        super(exception);
    }
}
