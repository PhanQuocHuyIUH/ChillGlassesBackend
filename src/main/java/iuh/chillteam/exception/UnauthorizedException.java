package iuh.chillteam.exception;

/**
 * Unauthorized exception (401)
 */
public class UnauthorizedException extends BaseException {
    public UnauthorizedException(String message) {
        super(message);
    }
}
