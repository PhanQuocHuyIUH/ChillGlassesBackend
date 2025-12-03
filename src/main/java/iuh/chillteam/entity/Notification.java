package iuh.chillteam.entity;

import iuh.chillteam.entity.enums.NotificationType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Notification Entity
 * Quản lý thông báo gửi đến người dùng
 */
@Entity
@Table(name = "notification")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * User nhận thông báo
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * Tiêu đề thông báo
     */
    @Column(nullable = false)
    private String title;

    /**
     * Nội dung thông báo
     */
    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;

    /**
     * Loại thông báo
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationType type;

    /**
     * Trạng thái đã đọc
     */
    @Column(name = "is_read", nullable = false)
    private Boolean isRead = false;

    /**
     * Thời gian tạo thông báo
     */
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * Set created_at before persist
     */
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        if (this.isRead == null) {
            this.isRead = false;
        }
    }
}
