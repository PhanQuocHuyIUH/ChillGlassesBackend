package iuh.chillteam.service;

import iuh.chillteam.dto.auth.ChangePasswordRequest;
import iuh.chillteam.dto.auth.ProfileUpdateRequest;
import iuh.chillteam.dto.auth.UserDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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

    UserDTO updateAvatar(MultipartFile file)  throws IOException;
}
