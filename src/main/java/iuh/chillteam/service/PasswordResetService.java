package iuh.chillteam.service;

import iuh.chillteam.entity.User;
import iuh.chillteam.exception.BadRequestException;
import iuh.chillteam.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

public interface PasswordResetService {
    void resetPassword(String email, String newPassword);
}
