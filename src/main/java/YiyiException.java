/**
 * Represents a custom exception for the Yiyi chatbot application.
 * Used for handling application-specific errors.
 */
public class YiyiException extends Exception{

    /**
     * Constructs a new YiyiException with the specified error message.
     *
     * @param message The error message describing the exception.
     */
    public YiyiException(String message){
        super(message);
    }

    /**
     * Constructs a new YiyiException with the specified error message and cause.
     *
     * @param message The error message describing the exception.
     * @param cause The cause of the exception.
     */
    public YiyiException(String message, Throwable cause){
        super(message, cause);
    }
}
