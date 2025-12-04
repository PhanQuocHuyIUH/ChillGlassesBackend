package iuh.chillteam.controller;

import iuh.chillteam.dto.auth.*;
import iuh.chillteam.dto.otp.ResetPasswordRequest;
import iuh.chillteam.service.impl.OtpService;
import iuh.chillteam.service.impl.PasswordResetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/password")
@RequiredArgsConstructor
public class ForgotPasswordController {

    private final OtpService otpService;
    private final PasswordResetService passwordResetService;

    @PostMapping("/send-otp")
    public void sendOtp(@RequestParam String email) {
        otpService.sendOtp(email);
    }

    @PostMapping("/verify-otp")
    public void verifyOtp(@RequestParam String email, @RequestParam String otp) {
        otpService.verifyOtp(email, otp);
    }

    @PostMapping("/reset")
    public void resetPassword(@RequestBody ResetPasswordRequest request) {
        passwordResetService.resetPassword(request.getEmail(), request.getNewPassword());
    }
}
