package iuh.chillteam.exception;

/**
 * Conflict exception (409)
 */
public class ConflictException extends BaseException {
    public ConflictException(String message) {
        super(message);
    }
}