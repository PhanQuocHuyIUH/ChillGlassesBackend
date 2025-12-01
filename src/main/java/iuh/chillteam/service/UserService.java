package iuh.chillteam.service;

import iuh.chillteam.dto.auth.ChangePasswordRequest;
import iuh.chillteam.dto.auth.ProfileUpdateRequest;
import iuh.chillteam.dto.auth.UserDTO;

/**
 * User Service Interface
 */
public interface UserService {

    /**
     * Get current user profile
     */
    UserDTO getCurrentUser();

    /**
     * Get user by ID
     */
    UserDTO getUserById(Long id);

    /**
     * Update user profile
     */
    UserDTO updateProfile(ProfileUpdateRequest request);

    /**
     * Change password
     */
    void changePassword(ChangePasswordRequest request);

    /**
     * Deactivate account (soft delete)
     */
    void deactivateAccount();
}
