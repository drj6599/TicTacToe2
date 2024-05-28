package exception;

public class CellIsOccupiedException extends RuntimeException {
    public CellIsOccupiedException() {
    }

    public CellIsOccupiedException(String message) {
        super(message);
    }

    public CellIsOccupiedException(String message, Throwable cause) {
        super(message, cause);
    }

    public CellIsOccupiedException(Throwable cause) {
        super(cause);
    }

    public CellIsOccupiedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
