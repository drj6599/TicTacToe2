package exception;

public class InvalidNumberOfBotsException extends RuntimeException {
    public InvalidNumberOfBotsException() {
    }

    public InvalidNumberOfBotsException(String message) {
        super(message);
    }

    public InvalidNumberOfBotsException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidNumberOfBotsException(Throwable cause) {
        super(cause);
    }

    public InvalidNumberOfBotsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
