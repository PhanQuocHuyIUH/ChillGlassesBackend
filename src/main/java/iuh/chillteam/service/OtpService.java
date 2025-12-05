package iuh.chillteam.service;

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

public interface OtpService {

    void sendOtp(String email);
    void verifyOtp(String email, String otp);
}
