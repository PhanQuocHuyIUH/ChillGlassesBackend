package iuh.chillteam.dto.otp;


import lombok.Data;

@Data
public class ResetPasswordRequest {
    private String email;
    private String newPassword;
}