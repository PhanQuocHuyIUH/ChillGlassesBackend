package iuh.chillteam.entity;

import iuh.chillteam.entity.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;

/**
 * User Entity - Quản lý người dùng (Customer và Admin)
 */
@Entity
@Table(name = "user", indexes = {
        @Index(name = "idx_email", columnList = "email"),
        @Index(name = "idx_role", columnList = "role")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseEntity {

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "address", columnDefinition = "TEXT")
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    @Builder.Default
    private UserRole role = UserRole.CUSTOMER;

    @Column(name = "is_active")
    @Builder.Default
    private Boolean isActive = true;

    @Column(name = "email_verified")
    @Builder.Default
    private Boolean emailVerified = false;

    // ────────── Thông tin bổ sung ──────────

    @Column(name = "day_of_birth")
    private Integer day;

    @Column(name = "month_of_birth")
    private Integer month;

    @Column(name = "year_of_birth")
    private Integer year;

    @Column(name = "gender", length = 10)
    private String gender;

    @Column(name = "recovery_email")
    private String recoveryEmail;

    @Column(name = "avatar")
    private String avatar; // lưu URL avatar


    @Override
    public String toString() {
        return "User{" +
                "id=" + getId() +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", isActive=" + isActive +
                ", day=" + day +
                ", month=" + month +
                ", year=" + year +
                ", gender=" + gender +
                ", recoveryEmail=" + recoveryEmail +
                '}';
    }
}
