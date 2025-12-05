package iuh.chillteam.service;

import iuh.chillteam.dto.auth.UpdateUserRequest;
import iuh.chillteam.dto.auth.UserDTO;
import iuh.chillteam.entity.enums.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Admin User Service Interface
 * Handles admin operations on users
 */
public interface AdminUserService {

    /**
     * Get all users with pagination and filtering
     */
    Page<UserDTO> getAllUsers(String search, UserRole role, Boolean isActive, Pageable pageable);

    /**
     * Get user by ID
     */
    UserDTO getUserById(Long userId);

    /**
     * Update user information
     */
    UserDTO updateUser(Long userId, UpdateUserRequest request);

    /**
     * Lock user account (set isActive = false)
     */
    UserDTO lockUser(Long userId);

    /**
     * Unlock user account (set isActive = true)
     */
    UserDTO unlockUser(Long userId);

    /**
     * Delete user (soft delete)
     */
    void deleteUser(Long userId);

    /**
     * Get total user count
     */
    Long getTotalUsers();

    /**
     * Get user count by role
     */
    Long getUserCountByRole(UserRole role);
}
