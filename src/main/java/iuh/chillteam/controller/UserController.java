package iuh.chillteam.controller;

import iuh.chillteam.dto.auth.ChangePasswordRequest;
import iuh.chillteam.dto.auth.ProfileUpdateRequest;
import iuh.chillteam.dto.auth.UserDTO;
import iuh.chillteam.dto.common.ApiResponse;
import iuh.chillteam.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * User Controller
 * Handles user profile management
 */
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "User", description = "User profile management endpoints")
@SecurityRequirement(name = "Bearer Authentication")
public class UserController {

    private final UserService userService;

    /**
     * Get current user profile
     */
    @GetMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "Get current user profile", description = "Get authenticated user's profile information")
    public ResponseEntity<ApiResponse<UserDTO>> getCurrentProfile() {
        log.info("GET /api/user/profile - Get current user profile");
        UserDTO user = userService.getCurrentUser();
        return ResponseEntity.ok(ApiResponse.success(user));
    }

    /**
     * Get user by ID
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get user by ID", description = "Admin only - Get user profile by ID")
    public ResponseEntity<ApiResponse<UserDTO>> getUserById(@PathVariable Long id) {
        log.info("GET /api/user/{} - Get user by ID", id);
        UserDTO user = userService.getUserById(id);
        return ResponseEntity.ok(ApiResponse.success(user));
    }

    /**
     * Update user profile
     */
    @PutMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "Update profile", description = "Update authenticated user's profile")
    public ResponseEntity<ApiResponse<UserDTO>> updateProfile(
            @Valid @RequestBody ProfileUpdateRequest request
    ) {
        log.info("PUT /api/user/profile - Update user profile");
        UserDTO user = userService.updateProfile(request);
        return ResponseEntity.ok(ApiResponse.success("Profile updated successfully", user));
    }

    /**
     * Change password
     */
    @PutMapping("/change-password")
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "Change password", description = "Change user password")
    public ResponseEntity<ApiResponse<Void>> changePassword(
            @Valid @RequestBody ChangePasswordRequest request
    ) {
        log.info("PUT /api/user/change-password - Change password");
        userService.changePassword(request);
        return ResponseEntity.ok(ApiResponse.success("Password changed successfully"));
    }

    /**
     * Note: Account deactivation (soft delete) is now handled by Admin only
     * through /api/admin/users/{id}/lock endpoint
     * This prevents users from self-deactivating their accounts
     */

    @PostMapping("/profile/avatar")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<UserDTO>> uploadAvatar(@RequestParam("file") MultipartFile file) {
        try {
            UserDTO updatedUser = userService.updateAvatar(file);
            return ResponseEntity.ok(ApiResponse.success("Avatar updated successfully", updatedUser));
        } catch (RuntimeException | IOException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(400, "Failed to upload avatar: " + e.getMessage()));
        }
    }


}
