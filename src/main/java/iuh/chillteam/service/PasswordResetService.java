package iuh.chillteam.service.impl;

import iuh.chillteam.entity.User;
import iuh.chillteam.exception.BadRequestException;
import iuh.chillteam.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PasswordResetService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void resetPassword(String email, String newPassword) {
        User user = userRepository.findByEmailAndActive(email)
                .orElseThrow(() -> new BadRequestException("Email chưa đăng ký"));
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }
}
