package iuh.chillteam.exception;

/**
 * User not found exception
 */
public class UserNotFoundException extends ResourceNotFoundException {
    public UserNotFoundException(Long id) {
        super("User", "id", id);
    }

    public UserNotFoundException(String email) {
        super("User", "email", email);
    }
}