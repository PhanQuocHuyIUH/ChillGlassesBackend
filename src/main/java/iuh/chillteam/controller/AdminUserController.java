package iuh.chillteam.controller;

import iuh.chillteam.dto.auth.UpdateUserRequest;
import iuh.chillteam.dto.auth.UserDTO;
import iuh.chillteam.dto.common.ApiResponse;
import iuh.chillteam.dto.common.PageResponse;
import iuh.chillteam.entity.enums.UserRole;
import iuh.chillteam.service.AdminUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Admin User Controller
 * Handles admin operations on users
 */
@RestController
@RequestMapping("/api/admin/users")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Admin - User Management", description = "Admin endpoints for user management")
@SecurityRequirement(name = "Bearer Authentication")
@PreAuthorize("hasRole('ADMIN')")
public class AdminUserController {

    private final AdminUserService adminUserService;

    /**
     * Get all users with filtering and pagination
     */
    @GetMapping
    @Operation(summary = "Get all users (Admin)", description = "Get all users with filtering and pagination")
    public ResponseEntity<ApiResponse<PageResponse<UserDTO>>> getAllUsers(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) UserRole role,
            @RequestParam(required = false) Boolean isActive,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "DESC") String sortDir
    ) {
        log.info("GET /api/admin/users - Get all users (Admin) - search: {}, role: {}, isActive: {}", 
                search, role, isActive);

        Sort sort = sortDir.equalsIgnoreCase("ASC") ? 
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<UserDTO> users = adminUserService.getAllUsers(search, role, isActive, pageable);

        PageResponse<UserDTO> pageResponse = PageResponse.<UserDTO>builder()
                .content(users.getContent())
                .pageNumber(users.getNumber())
                .pageSize(users.getSize())
                .totalElements(users.getTotalElements())
                .totalPages(users.getTotalPages())
                .last(users.isLast())
                .build();

        return ResponseEntity.ok(ApiResponse.success("Users retrieved successfully", pageResponse));
    }

    /**
     * Get user by ID
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get user by ID (Admin)", description = "Get user details by ID")
    public ResponseEntity<ApiResponse<UserDTO>> getUserById(@PathVariable Long id) {
        log.info("GET /api/admin/users/{} - Get user by ID (Admin)", id);
        UserDTO user = adminUserService.getUserById(id);
        return ResponseEntity.ok(ApiResponse.success("User retrieved successfully", user));
    }

    /**
     * Update user
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update user (Admin)", description = "Update user information")
    public ResponseEntity<ApiResponse<UserDTO>> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UpdateUserRequest request
    ) {
        log.info("PUT /api/admin/users/{} - Update user (Admin)", id);
        UserDTO user = adminUserService.updateUser(id, request);
        return ResponseEntity.ok(ApiResponse.success("User updated successfully", user));
    }

    /**
     * Lock user account
     */
    @PostMapping("/{id}/lock")
    @Operation(
        summary = "Lock user (Admin)", 
        description = "Lock user account by setting isActive=false and performing soft delete. Locked users cannot login and are marked as deleted."
    )
    public ResponseEntity<ApiResponse<UserDTO>> lockUser(@PathVariable Long id) {
        log.info("POST /api/admin/users/{}/lock - Lock user (Admin)", id);
        UserDTO user = adminUserService.lockUser(id);
        return ResponseEntity.ok(ApiResponse.success("User locked successfully", user));
    }

    /**
     * Unlock user account
     */
    @PostMapping("/{id}/unlock")
    @Operation(
        summary = "Unlock user (Admin)", 
        description = "Unlock user account by setting isActive=true and restoring from soft delete. User can login again."
    )
    public ResponseEntity<ApiResponse<UserDTO>> unlockUser(@PathVariable Long id) {
        log.info("POST /api/admin/users/{}/unlock - Unlock user (Admin)", id);
        UserDTO user = adminUserService.unlockUser(id);
        return ResponseEntity.ok(ApiResponse.success("User unlocked successfully", user));
    }

    /**
     * Delete user
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user (Admin)", description = "Delete user (cannot delete admin)")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable Long id) {
        log.info("DELETE /api/admin/users/{} - Delete user (Admin)", id);
        adminUserService.deleteUser(id);
        return ResponseEntity.ok(ApiResponse.success("User deleted successfully"));
    }

    /**
     * Get total users count
     */
    @GetMapping("/count")
    @Operation(summary = "Get total users count (Admin)", description = "Get total number of users")
    public ResponseEntity<ApiResponse<Long>> getTotalUsers() {
        log.info("GET /api/admin/users/count - Get total users count (Admin)");
        Long count = adminUserService.getTotalUsers();
        return ResponseEntity.ok(ApiResponse.success("Total users count retrieved successfully", count));
    }

    /**
     * Get user count by role
     */
    @GetMapping("/count/role/{role}")
    @Operation(summary = "Get user count by role (Admin)", description = "Get number of users by role")
    public ResponseEntity<ApiResponse<Long>> getUserCountByRole(@PathVariable UserRole role) {
        log.info("GET /api/admin/users/count/role/{} - Get user count by role (Admin)", role);
        Long count = adminUserService.getUserCountByRole(role);
        return ResponseEntity.ok(ApiResponse.success("User count by role retrieved successfully", count));
    }
}
