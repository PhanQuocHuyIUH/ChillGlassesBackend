package iuh.chillteam.repository;

import iuh.chillteam.entity.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Notification Repository
 * Data access layer for Notification entity
 */
@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    /**
     * Find all notifications by user ID
     */
    @Query("SELECT n FROM Notification n WHERE n.user.id = :userId ORDER BY n.createdAt DESC")
    List<Notification> findByUserId(@Param("userId") Long userId);

    /**
     * Find all notifications by user ID with pagination
     */
    @Query("SELECT n FROM Notification n WHERE n.user.id = :userId ORDER BY n.createdAt DESC")
    Page<Notification> findByUserId(@Param("userId") Long userId, Pageable pageable);

    /**
     * Find notifications by user ID and read status
     */
    @Query("SELECT n FROM Notification n WHERE n.user.id = :userId AND n.isRead = :isRead ORDER BY n.createdAt DESC")
    List<Notification> findByUserIdAndIsRead(@Param("userId") Long userId, @Param("isRead") Boolean isRead);

    /**
     * Count unread notifications by user ID
     */
    @Query("SELECT COUNT(n) FROM Notification n WHERE n.user.id = :userId AND n.isRead = false")
    Long countByUserIdAndIsRead(@Param("userId") Long userId);

    /**
     * Find unread notifications by user ID
     */
    @Query("SELECT n FROM Notification n WHERE n.user.id = :userId AND n.isRead = false ORDER BY n.createdAt DESC")
    List<Notification> findUnreadByUserId(@Param("userId") Long userId);
}
