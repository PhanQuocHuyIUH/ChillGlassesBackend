package iuh.chillteam.service.impl;

import iuh.chillteam.dto.notification.NotificationDTO;
import iuh.chillteam.entity.Notification;
import iuh.chillteam.entity.User;
import iuh.chillteam.entity.enums.NotificationType;
import iuh.chillteam.exception.ForbiddenException;
import iuh.chillteam.exception.ResourceNotFoundException;
import iuh.chillteam.exception.UserNotFoundException;
import iuh.chillteam.repository.NotificationRepository;
import iuh.chillteam.repository.UserRepository;
import iuh.chillteam.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Notification Service Implementation
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    @Override
    public NotificationDTO createNotification(Long userId, String title, String message, NotificationType type) {
        log.info("Creating notification for user: {}", userId);

        // Get user
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        // Create notification
        Notification notification = Notification.builder()
                .user(user)
                .title(title)
                .message(message)
                .type(type)
                .isRead(false)
                .build();

        notification = notificationRepository.save(notification);
        log.info("Created notification: {}", notification.getId());

        return convertToDTO(notification);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NotificationDTO> getMyNotifications(Long userId) {
        log.info("Getting notifications for user: {}", userId);

        List<Notification> notifications = notificationRepository.findByUserId(userId);
        return notifications.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<NotificationDTO> getMyNotifications(Long userId, Pageable pageable) {
        log.info("Getting notifications for user: {} with pagination", userId);

        Page<Notification> notifications = notificationRepository.findByUserId(userId, pageable);
        return notifications.map(this::convertToDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NotificationDTO> getUnreadNotifications(Long userId) {
        log.info("Getting unread notifications for user: {}", userId);

        List<Notification> notifications = notificationRepository.findUnreadByUserId(userId);
        return notifications.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public NotificationDTO markAsRead(Long notificationId, Long userId) {
        log.info("Marking notification {} as read by user: {}", notificationId, userId);

        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new ResourceNotFoundException("Notification not found"));

        // Check ownership
        if (!notification.getUser().getId().equals(userId)) {
            throw new ForbiddenException("You don't have permission to access this notification");
        }

        notification.setIsRead(true);
        notification = notificationRepository.save(notification);

        log.info("Marked notification {} as read", notificationId);
        return convertToDTO(notification);
    }

    @Override
    public void markAllAsRead(Long userId) {
        log.info("Marking all notifications as read for user: {}", userId);

        List<Notification> unreadNotifications = notificationRepository.findUnreadByUserId(userId);

        for (Notification notification : unreadNotifications) {
            notification.setIsRead(true);
        }

        notificationRepository.saveAll(unreadNotifications);
        log.info("Marked {} notifications as read", unreadNotifications.size());
    }

    @Override
    public void deleteNotification(Long notificationId, Long userId) {
        log.info("Deleting notification {} by user: {}", notificationId, userId);

        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new ResourceNotFoundException("Notification not found"));

        // Check ownership
        if (!notification.getUser().getId().equals(userId)) {
            throw new ForbiddenException("You don't have permission to delete this notification");
        }

        notificationRepository.delete(notification);
        log.info("Deleted notification: {}", notificationId);
    }

    @Override
    @Transactional(readOnly = true)
    public Long getUnreadCount(Long userId) {
        log.info("Getting unread count for user: {}", userId);
        return notificationRepository.countByUserIdAndIsRead(userId);
    }

    /**
     * Convert Notification to NotificationDTO
     */
    private NotificationDTO convertToDTO(Notification notification) {
        return NotificationDTO.builder()
                .id(notification.getId())
                .userId(notification.getUser().getId())
                .title(notification.getTitle())
                .message(notification.getMessage())
                .type(notification.getType())
                .isRead(notification.getIsRead())
                .createdAt(notification.getCreatedAt())
                .build();
    }
}
