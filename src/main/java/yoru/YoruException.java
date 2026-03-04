package yoru;

/**
 * Represents user-facing errors produced by command parsing or task operations.
 */
public class YoruException extends Exception {
    /**
     * Creates a new Yoru exception with the provided message.
     *
     * @param message Error message.
     */
    public YoruException(String message) {
        super(message);
    }
}