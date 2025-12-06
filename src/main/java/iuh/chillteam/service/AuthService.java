package iuh.chillteam.service;

import iuh.chillteam.dto.auth.*;

/**
 * Authentication Service Interface
 */
public interface AuthService {

    /**
     * Register new user
     */
    AuthResponse register(RegisterRequest request);

    /**
     * Login user
     */
    AuthResponse login(LoginRequest request);

    /**
     * Refresh access token
     */
    AuthResponse refreshToken(String refreshToken);

    /**
     * Logout user (optional - for token blacklist)
     */
    void logout(String token);

    /**
     * Verify email (optional - for email verification)
     */
    void verifyEmail(String token);
    AuthResponse loginWithGoogle(String idToken);

}
