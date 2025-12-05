package iuh.chillteam.service.impl;

import iuh.chillteam.dto.auth.UpdateUserRequest;
import iuh.chillteam.dto.auth.UserDTO;
import iuh.chillteam.entity.User;
import iuh.chillteam.entity.enums.UserRole;
import iuh.chillteam.exception.BadRequestException;
import iuh.chillteam.exception.ConflictException;
import iuh.chillteam.exception.UserNotFoundException;
import iuh.chillteam.repository.UserRepository;
import iuh.chillteam.service.AdminUserService;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Admin User Service Implementation
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AdminUserServiceImpl implements AdminUserService {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<UserDTO> getAllUsers(String search, UserRole role, Boolean isActive, Pageable pageable) {
        log.info("Getting all users with filters - search: {}, role: {}, isActive: {}", search, role, isActive);

        Specification<User> spec = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Search by name or email
            if (search != null && !search.trim().isEmpty()) {
                String searchPattern = "%" + search.toLowerCase() + "%";
                Predicate namePredicate = criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("fullName")), searchPattern);
                Predicate emailPredicate = criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("email")), searchPattern);
                predicates.add(criteriaBuilder.or(namePredicate, emailPredicate));
            }

            // Filter by role
            if (role != null) {
                predicates.add(criteriaBuilder.equal(root.get("role"), role));
            }

            // Filter by active status
            if (isActive != null) {
                predicates.add(criteriaBuilder.equal(root.get("isActive"), isActive));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        Page<User> users = userRepository.findAll(spec, pageable);
        return users.map(this::convertToDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO getUserById(Long userId) {
        log.info("Getting user by ID: {}", userId);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

        return convertToDTO(user);
    }

    @Override
    public UserDTO updateUser(Long userId, UpdateUserRequest request) {
        log.info("Updating user: {}", userId);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

        // Update fields if provided
        if (request.getFullName() != null) {
            user.setFullName(request.getFullName());
        }

        if (request.getEmail() != null && !request.getEmail().equals(user.getEmail())) {
            // Check if email already exists
            if (userRepository.existsByEmail(request.getEmail())) {
                throw new ConflictException("Email already exists: " + request.getEmail());
            }
            user.setEmail(request.getEmail());
        }

        if (request.getPhone() != null) {
            user.setPhone(request.getPhone());
        }

        if (request.getAddress() != null) {
            user.setAddress(request.getAddress());
        }

        if (request.getRole() != null) {
            user.setRole(request.getRole());
        }

        if (request.getIsActive() != null) {
            user.setIsActive(request.getIsActive());
        }

        if (request.getEmailVerified() != null) {
            user.setEmailVerified(request.getEmailVerified());
        }

        user = userRepository.save(user);
        log.info("Updated user: {}", userId);

        return convertToDTO(user);
    }

    @Override
    public UserDTO lockUser(Long userId) {
        log.info("Locking user: {}", userId);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

        if (!user.getIsActive()) {
            throw new BadRequestException("User is already locked");
        }

        user.setIsActive(false);
        user = userRepository.save(user);

        log.info("Locked user: {}", userId);
        return convertToDTO(user);
    }

    @Override
    public UserDTO unlockUser(Long userId) {
        log.info("Unlocking user: {}", userId);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

        if (user.getIsActive()) {
            throw new BadRequestException("User is already unlocked");
        }

        user.setIsActive(true);
        user = userRepository.save(user);

        log.info("Unlocked user: {}", userId);
        return convertToDTO(user);
    }

    @Override
    public void deleteUser(Long userId) {
        log.info("Deleting user: {}", userId);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

        // Prevent deleting admin users
        if (user.getRole() == UserRole.ADMIN) {
            throw new BadRequestException("Cannot delete admin user");
        }

        userRepository.delete(user);
        log.info("Deleted user: {}", userId);
    }

    @Override
    @Transactional(readOnly = true)
    public Long getTotalUsers() {
        return userRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Long getUserCountByRole(UserRole role) {
        return userRepository.countByRole(role);
    }

    /**
     * Convert User to UserDTO
     */
    private UserDTO convertToDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .address(user.getAddress())
                .role(user.getRole().toString())
                .isActive(user.getIsActive())
                .emailVerified(user.getEmailVerified())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
