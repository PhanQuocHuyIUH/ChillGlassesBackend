package iuh.chillteam.entity.enums;

/**
 * Review Status Enum
 * Status của review: PENDING (chờ duyệt), APPROVED (đã duyệt), REJECTED (từ chối)
 */
public enum ReviewStatus {
    PENDING,    // Chờ admin duyệt
    APPROVED,   // Admin đã duyệt - hiển thị public
    REJECTED    // Admin từ chối - không hiển thị
}
