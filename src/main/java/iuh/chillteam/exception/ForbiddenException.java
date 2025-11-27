package iuh.chillteam.exception;

/**
 * Forbidden exception (403)
 */
public class ForbiddenException extends BaseException {
    public ForbiddenException(String message) {
        super(message);
    }
}