package exception;

public class InvalidCellInputException extends RuntimeException {
    public InvalidCellInputException() {
    }

    public InvalidCellInputException(String message) {
        super(message);
    }

    public InvalidCellInputException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidCellInputException(Throwable cause) {
        super(cause);
    }

    public InvalidCellInputException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
