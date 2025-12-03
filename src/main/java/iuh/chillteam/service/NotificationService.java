package iuh.chillteam.service;

import iuh.chillteam.dto.notification.NotificationDTO;
import iuh.chillteam.entity.enums.NotificationType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Notification Service Interface
 */
public interface NotificationService {

    /**
     * Create notification for user
     */
    NotificationDTO createNotification(Long userId, String title, String message, NotificationType type);

    /**
     * Get all notifications for user
     */
    List<NotificationDTO> getMyNotifications(Long userId);

    /**
     * Get notifications with pagination
     */
    Page<NotificationDTO> getMyNotifications(Long userId, Pageable pageable);

    /**
     * Get unread notifications
     */
    List<NotificationDTO> getUnreadNotifications(Long userId);

    /**
     * Mark notification as read
     */
    NotificationDTO markAsRead(Long notificationId, Long userId);

    /**
     * Mark all notifications as read
     */
    void markAllAsRead(Long userId);

    /**
     * Delete notification
     */
    void deleteNotification(Long notificationId, Long userId);

    /**
     * Get unread notification count
     */
    Long getUnreadCount(Long userId);
}
