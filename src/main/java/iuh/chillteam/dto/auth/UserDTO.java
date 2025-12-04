package iuh.chillteam.dto.auth;

import com.fasterxml.jackson.annotation.JsonInclude;
import iuh.chillteam.entity.User;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * User DTO - Response
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

    private Long id;

    private String fullName;

    private String email;

    private String phone;

    private String address;

    private String role;

    private Boolean isActive;

    private Boolean emailVerified;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Integer day;
    private Integer month;
    private Integer year;
    private String gender;
    private String recoveryEmail;
    private String avatar;



    /**
     * Convert User entity to DTO
     */
    public static UserDTO fromEntity(User user) {
        if (user == null) {
            return null;
        }

        return UserDTO.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .address(user.getAddress())
                .role(user.getRole().name())
                .isActive(user.getIsActive())
                .emailVerified(user.getEmailVerified())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .day(user.getDay())               // thêm
                .month(user.getMonth())           // thêm
                .year(user.getYear())             // thêm
                .gender(user.getGender())         // thêm
                .recoveryEmail(user.getRecoveryEmail()) // thêm
                .avatar(user.getAvatar()) // thêm vào builder
                .build();
    }
}
