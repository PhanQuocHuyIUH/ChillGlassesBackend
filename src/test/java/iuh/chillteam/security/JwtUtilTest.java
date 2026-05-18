package iuh.chillteam.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

class JwtUtilTest {

    private JwtUtil jwtUtil;
    private UserDetails userDetails;

    @BeforeEach
    void setUp() {
        jwtUtil = new JwtUtil();
        ReflectionTestUtils.setField(
                jwtUtil,
                "secret",
                "0123456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef"
        );
        ReflectionTestUtils.setField(jwtUtil, "expiration", 86400000L);
        ReflectionTestUtils.setField(jwtUtil, "refreshExpiration", 604800000L);

        userDetails = User.withUsername("user@example.com")
                .password("dummy")
                .authorities("ROLE_CUSTOMER")
                .build();
    }

    @Test
    void shouldMarkRefreshTokenWithRefreshType() {
        String refreshToken = jwtUtil.generateRefreshToken(userDetails);

        assertTrue(jwtUtil.isRefreshToken(refreshToken));
        assertEquals("refresh", jwtUtil.extractTokenType(refreshToken));
    }

    @Test
    void shouldNotTreatAccessTokenAsRefreshToken() {
        String accessToken = jwtUtil.generateToken(userDetails);

        assertFalse(jwtUtil.isRefreshToken(accessToken));
        assertNull(jwtUtil.extractTokenType(accessToken));
    }
}
