package iuh.chillteam.controller;

import iuh.chillteam.dto.common.ApiResponse;
import iuh.chillteam.dto.common.PageResponse;
import iuh.chillteam.dto.notification.NotificationDTO;
import iuh.chillteam.security.UserDetailsServiceImpl;
import iuh.chillteam.service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Notification Controller
 * Handles notification operations
 */
@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Notification", description = "Notification management endpoints")
@SecurityRequirement(name = "Bearer Authentication")
public class NotificationController {

    private final NotificationService notificationService;

    /**
     * Get my notifications (paginated)
     */
    @GetMapping
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "Get my notifications", description = "Get all notifications for current user with pagination")
    public ResponseEntity<ApiResponse<PageResponse<NotificationDTO>>> getMyNotifications(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @AuthenticationPrincipal UserDetailsServiceImpl.CustomUserDetails userDetails
    ) {
        log.info("GET /api/notifications - Get notifications for user: {}", userDetails.getUsername());
        Pageable pageable = PageRequest.of(page, size);
        Page<NotificationDTO> notifications = notificationService.getMyNotifications(userDetails.getUserId(), pageable);

        PageResponse<NotificationDTO> pageResponse = PageResponse.<NotificationDTO>builder()
                .content(notifications.getContent())
                .pageNumber(notifications.getNumber())
                .pageSize(notifications.getSize())
                .totalElements(notifications.getTotalElements())
                .totalPages(notifications.getTotalPages())
                .last(notifications.isLast())
                .build();

        return ResponseEntity.ok(ApiResponse.success("Notifications retrieved successfully", pageResponse));
    }

    /**
     * Get unread notifications
     */
    @GetMapping("/unread")
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "Get unread notifications", description = "Get all unread notifications for current user")
    public ResponseEntity<ApiResponse<List<NotificationDTO>>> getUnreadNotifications(
            @AuthenticationPrincipal UserDetailsServiceImpl.CustomUserDetails userDetails
    ) {
        log.info("GET /api/notifications/unread - Get unread notifications for user: {}", userDetails.getUsername());
        List<NotificationDTO> notifications = notificationService.getUnreadNotifications(userDetails.getUserId());
        return ResponseEntity.ok(ApiResponse.success("Unread notifications retrieved successfully", notifications));
    }

    /**
     * Get unread notification count
     */
    @GetMapping("/unread/count")
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "Get unread count", description = "Get count of unread notifications")
    public ResponseEntity<ApiResponse<Long>> getUnreadCount(
            @AuthenticationPrincipal UserDetailsServiceImpl.CustomUserDetails userDetails
    ) {
        log.info("GET /api/notifications/unread/count - Get unread count for user: {}", userDetails.getUsername());
        Long count = notificationService.getUnreadCount(userDetails.getUserId());
        return ResponseEntity.ok(ApiResponse.success("Unread count retrieved successfully", count));
    }

    /**
     * Mark notification as read
     */
    @PutMapping("/{id}/read")
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "Mark as read", description = "Mark a notification as read")
    public ResponseEntity<ApiResponse<NotificationDTO>> markAsRead(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetailsServiceImpl.CustomUserDetails userDetails
    ) {
        log.info("PUT /api/notifications/{}/read - Mark as read", id);
        NotificationDTO notification = notificationService.markAsRead(id, userDetails.getUserId());
        return ResponseEntity.ok(ApiResponse.success("Notification marked as read", notification));
    }

    /**
     * Mark all notifications as read
     */
    @PutMapping("/read-all")
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "Mark all as read", description = "Mark all notifications as read")
    public ResponseEntity<ApiResponse<Void>> markAllAsRead(
            @AuthenticationPrincipal UserDetailsServiceImpl.CustomUserDetails userDetails
    ) {
        log.info("PUT /api/notifications/read-all - Mark all as read for user: {}", userDetails.getUsername());
        notificationService.markAllAsRead(userDetails.getUserId());
        return ResponseEntity.ok(ApiResponse.success("All notifications marked as read"));
    }

    /**
     * Delete notification
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "Delete notification", description = "Delete a notification")
    public ResponseEntity<ApiResponse<Void>> deleteNotification(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetailsServiceImpl.CustomUserDetails userDetails
    ) {
        log.info("DELETE /api/notifications/{} - Delete notification", id);
        notificationService.deleteNotification(id, userDetails.getUserId());
        return ResponseEntity.ok(ApiResponse.success("Notification deleted successfully"));
    }
}
