package iuh.chillteam.service.impl;

import iuh.chillteam.dto.auth.ChangePasswordRequest;
import iuh.chillteam.dto.auth.ProfileUpdateRequest;
import iuh.chillteam.dto.auth.UserDTO;
import iuh.chillteam.entity.User;
import iuh.chillteam.exception.BadRequestException;
import iuh.chillteam.exception.ResourceNotFoundException;
import iuh.chillteam.exception.UnauthorizedException;
import iuh.chillteam.repository.UserRepository;
import iuh.chillteam.security.UserDetailsServiceImpl;
import iuh.chillteam.service.CloudinaryService;
import iuh.chillteam.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * User Service Implementation
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CloudinaryService cloudinaryService;

    @Override
    @Transactional(readOnly = true)
    public UserDTO getCurrentUser() {
        User user = getCurrentAuthenticatedUser();
        log.info("Getting current user: {}", user.getEmail());
        return UserDTO.fromEntity(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO getUserById(Long id) {
        log.info("Getting user by ID: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        if (user.getDeletedAt() != null) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }

        return UserDTO.fromEntity(user);
    }

    @Override
    public UserDTO updateProfile(ProfileUpdateRequest request) {
        User user = getCurrentAuthenticatedUser();
        log.info("Updating profile for user: {}", user.getEmail());

        // Update fields if provided
        if (request.getFullName() != null) {
            user.setFullName(request.getFullName());
        }

        if (request.getPhone() != null) {
            user.setPhone(request.getPhone());
        }

        if (request.getAddress() != null) {
            user.setAddress(request.getAddress());
        }

        user = userRepository.save(user);
        log.info("Profile updated successfully for user: {}", user.getEmail());

        return UserDTO.fromEntity(user);
    }

    @Override
    public void changePassword(ChangePasswordRequest request) {
        User user = getCurrentAuthenticatedUser();
        log.info("Changing password for user: {}", user.getEmail());

        // Verify current password
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new BadRequestException("Current password is incorrect");
        }

        // Verify new password confirmation
        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            throw new BadRequestException("New password and confirm password do not match");
        }

        // Check if new password is same as current
        if (request.getCurrentPassword().equals(request.getNewPassword())) {
            throw new BadRequestException("New password must be different from current password");
        }

        // Update password
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);

        log.info("Password changed successfully for user: {}", user.getEmail());
    }

    @Override
    public void deactivateAccount() {
        User user = getCurrentAuthenticatedUser();
        log.info("Deactivating account for user: {}", user.getEmail());

        user.softDelete();
        userRepository.save(user);

        log.info("Account deactivated successfully for user: {}", user.getEmail());
    }

    @Override
    public UserDTO updateAvatar(MultipartFile file) throws IOException {
        User user = getCurrentAuthenticatedUser();
        String imageUrl = cloudinaryService.uploadUserAvatar(file); // mới: upload avatar
        user.setAvatar(imageUrl);
        userRepository.save(user);
        return UserDTO.fromEntity(user);
    }

    /**
     * Get current authenticated user from security context
     */
    private User getCurrentAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UnauthorizedException("User not authenticated");
        }

        if (authentication.getPrincipal() instanceof UserDetailsServiceImpl.CustomUserDetails userDetails) {
            return userDetails.getUser();
        }

        throw new UnauthorizedException("Invalid authentication");
    }
}
