package iuh.chillteam.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

/**
 * Security Configuration
 *
 * Public routes (không cần authentication):
 * - GET /api/categories/**
 * - GET /api/products/**
 * - POST /api/auth/login
 * - POST /api/auth/register
 *
 * Protected routes (cần authentication):
 * - /api/cart/**
 * - /api/orders/**
 * - /api/profile/**
 * - /api/notifications/**
 * - /api/reviews/**
 *
 * Admin only routes:
 * - POST/PUT/DELETE /api/categories/**
 * - POST/PUT/DELETE /api/products/**
 * - /api/admin/**
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {

    /**
     * Password Encoder - BCrypt
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Security Filter Chain - Cấu hình phân quyền
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Disable CSRF (vì dùng JWT, không cần CSRF token)
                .csrf(AbstractHttpConfigurer::disable)

                // Enable CORS
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))

                // Session management - Stateless (dùng JWT)
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                // Authorization rules
                .authorizeHttpRequests(auth -> auth
                        // ============================================
                        // PUBLIC ROUTES (Không cần authentication)
                        // ============================================

                        // Auth endpoints
                        .requestMatchers("/api/auth/**").permitAll()

                        // Category endpoints (GET only)
                        .requestMatchers(HttpMethod.GET, "/api/categories/**").permitAll()

                        // Product endpoints (GET only)
                        .requestMatchers(HttpMethod.GET, "/api/products/**").permitAll()

                        // Swagger/OpenAPI documentation (nếu có)
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/swagger-resources/**",
                                "/webjars/**"
                        ).permitAll()

                        // Actuator health check (nếu có)
                        .requestMatchers("/actuator/health").permitAll()

                        // ============================================
                        // ADMIN ONLY ROUTES
                        // ============================================

                        // Category management (POST, PUT, DELETE)
                        .requestMatchers(HttpMethod.POST, "/api/categories/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/categories/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/api/categories/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/categories/**").hasRole("ADMIN")

                        // Product management (POST, PUT, DELETE)
                        .requestMatchers(HttpMethod.POST, "/api/products/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/products/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/api/products/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/products/**").hasRole("ADMIN")

                        // Admin dashboard & management
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")

                        // ============================================
                        // PROTECTED ROUTES (Cần authentication - CUSTOMER hoặc ADMIN)
                        // ============================================

                        // Cart
                        .requestMatchers("/api/cart/**").authenticated()

                        // Orders
                        .requestMatchers("/api/orders/**").authenticated()

                        // Profile
                        .requestMatchers("/api/users/me/**").authenticated()
                        .requestMatchers("/api/profile/**").authenticated()

                        // Reviews
                        .requestMatchers("/api/reviews/**").authenticated()

                        // Notifications
                        .requestMatchers("/api/notifications/**").authenticated()

                        // Wishlist (nếu có)
                        .requestMatchers("/api/wishlist/**").authenticated()

                        // ============================================
                        // TẤT CẢ ROUTES KHÁC - Deny by default (bảo mật)
                        // ============================================
                        .anyRequest().authenticated()
                );

        return http.build();
    }

    /**
     * CORS Configuration
     * Cho phép frontend (React) gọi API
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        // Allowed origins (Frontend URLs)
        configuration.setAllowedOrigins(Arrays.asList(
                "http://localhost:3000",      // Next.js dev
                "http://localhost:5173",      // Vite dev
                "https://yourdomain.com"      // Production
        ));

        // Allowed methods
        configuration.setAllowedMethods(Arrays.asList(
                "GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"
        ));

        // Allowed headers
        configuration.setAllowedHeaders(Arrays.asList("*"));

        // Allow credentials (cookies, authorization headers)
        configuration.setAllowCredentials(true);

        // Max age
        configuration.setMaxAge(3600L);

        // Exposed headers
        configuration.setExposedHeaders(Arrays.asList(
                "Authorization",
                "Content-Type",
                "X-Total-Count"
        ));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}