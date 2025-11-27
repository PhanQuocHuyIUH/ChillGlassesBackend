package iuh.chillteam.exception;

/**
 * Bad request exception (400)
 */
public class BadRequestException extends BaseException {
    public BadRequestException(String message) {
        super(message);
    }
}