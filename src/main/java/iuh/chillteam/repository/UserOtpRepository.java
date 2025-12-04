package iuh.chillteam.repository;

import iuh.chillteam.entity.UserOtp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserOtpRepository extends JpaRepository<UserOtp, Long> {
    Optional<UserOtp> findFirstByEmailAndOtpAndUsedFalse(String email, String otp);
}
