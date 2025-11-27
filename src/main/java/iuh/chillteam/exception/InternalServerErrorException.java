package iuh.chillteam.exception;

/**
 * Internal server error exception (500)
 */
public class InternalServerErrorException extends BaseException {
    public InternalServerErrorException(String message) {
        super(message);
    }

    public InternalServerErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}