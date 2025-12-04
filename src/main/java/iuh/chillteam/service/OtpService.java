package iuh.chillteam.service.impl;

import iuh.chillteam.entity.User;
import iuh.chillteam.entity.UserOtp;
import iuh.chillteam.exception.BadRequestException;
import iuh.chillteam.repository.UserOtpRepository;
import iuh.chillteam.repository.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Transactional
public class OtpService {

    private final UserRepository userRepository;
    private final UserOtpRepository userOtpRepository;
    private final JavaMailSender mailSender;

    public void sendOtp(String email) {
        // Check user exists
        User user = userRepository.findByEmailAndActive(email)
                .orElseThrow(() -> new BadRequestException("Email chưa đăng ký"));

        // Tạo OTP 6 số
        String otp = String.format("%06d", new Random().nextInt(999999));

        // Lưu OTP
        UserOtp userOtp = new UserOtp();
        userOtp.setEmail(email);
        userOtp.setOtp(otp);
        userOtp.setExpiryTime(LocalDateTime.now().plusMinutes(5)); // 5 phút
        userOtpRepository.save(userOtp);

        // Gửi mail
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(email);
            helper.setSubject("ChillGlasses - OTP đặt lại mật khẩu");
            helper.setText("Mã OTP của bạn là: " + otp + "\nHết hạn sau 5 phút", true);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Gửi OTP thất bại: " + e.getMessage());
        }
    }

    public void verifyOtp(String email, String otp) {
        UserOtp userOtp = userOtpRepository.findFirstByEmailAndOtpAndUsedFalse(email, otp)
                .orElseThrow(() -> new BadRequestException("OTP không hợp lệ hoặc đã hết hạn"));

        if (userOtp.getExpiryTime().isBefore(LocalDateTime.now())) {
            throw new BadRequestException("OTP đã hết hạn");
        }

        userOtp.setUsed(true);
        userOtpRepository.save(userOtp);
    }
}
