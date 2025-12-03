package iuh.chillteam.service.impl;

import iuh.chillteam.dto.auth.*;
import iuh.chillteam.entity.User;
import iuh.chillteam.entity.enums.UserRole;
import iuh.chillteam.exception.BadRequestException;
import iuh.chillteam.exception.ConflictException;
import iuh.chillteam.exception.UnauthorizedException;
import iuh.chillteam.repository.UserRepository;
import iuh.chillteam.security.JwtUtil;
import iuh.chillteam.security.UserDetailsServiceImpl;
import iuh.chillteam.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Authentication Service Implementation
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse register(RegisterRequest request) {
        log.info("Registering new user: {}", request.getEmail());

        // Check if email already exists
        if (userRepository.existsByEmailAndNotDeleted(request.getEmail())) {
            throw new ConflictException("Email already exists: " + request.getEmail());
        }

        // Create new user
        User user = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phone(request.getPhone())
                .address(request.getAddress())
                .role(UserRole.CUSTOMER)
                .isActive(true)
                .emailVerified(false)
                .build();

        user = userRepository.save(user);
        log.info("User registered successfully: {}", user.getEmail());

        // Generate tokens
        UserDetails userDetails = new UserDetailsServiceImpl.CustomUserDetails(user);
        String accessToken = jwtUtil.generateToken(userDetails);
        String refreshToken = jwtUtil.generateRefreshToken(userDetails);

        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .tokenType("Bearer")
                .expiresIn(86400L) // 24 hours
                .user(UserDTO.fromEntity(user))
                .build();
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        log.info("User login attempt: {}", request.getEmail());

        try {
            // Authenticate user
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );

            // Get user details
            UserDetailsServiceImpl.CustomUserDetails userDetails =
                    (UserDetailsServiceImpl.CustomUserDetails) authentication.getPrincipal();

            User user = userDetails.getUser();

            // Generate tokens
            String accessToken = jwtUtil.generateToken(userDetails);
            String refreshToken = jwtUtil.generateRefreshToken(userDetails);

            log.info("User logged in successfully: {}", user.getEmail());

            return AuthResponse.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .tokenType("Bearer")
                    .expiresIn(86400L) // 24 hours
                    .user(UserDTO.fromEntity(user))
                    .build();

        } catch (BadCredentialsException e) {
            log.error("Invalid credentials for user: {}", request.getEmail());
            throw new UnauthorizedException("Invalid email or password");
        }
    }

    @Override
    public AuthResponse refreshToken(String refreshToken) {
        log.info("Refreshing access token");

        try {
            // Validate refresh token
            if (!jwtUtil.validateToken(refreshToken)) {
                throw new UnauthorizedException("Invalid refresh token");
            }

            // Extract username from token
            String email = jwtUtil.extractUsername(refreshToken);

            // Load user
            User user = userRepository.findByEmailAndActive(email)
                    .orElseThrow(() -> new UnauthorizedException("User not found or inactive"));

            // Generate new access token
            UserDetails userDetails = new UserDetailsServiceImpl.CustomUserDetails(user);
            String newAccessToken = jwtUtil.generateToken(userDetails);

            log.info("Access token refreshed for user: {}", email);

            return AuthResponse.builder()
                    .accessToken(newAccessToken)
                    .refreshToken(refreshToken)
                    .tokenType("Bearer")
                    .expiresIn(86400L)
                    .user(UserDTO.fromEntity(user))
                    .build();

        } catch (Exception e) {
            log.error("Error refreshing token: {}", e.getMessage());
            throw new UnauthorizedException("Invalid refresh token");
        }
    }

    @Override
    public void logout(String token) {
        // TODO: Implement token blacklist if needed
        log.info("User logout");
    }

    @Override
    public void verifyEmail(String token) {
        // TODO: Implement email verification logic
        log.info("Email verification");
    }
}
