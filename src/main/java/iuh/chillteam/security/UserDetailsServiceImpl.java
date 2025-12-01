package iuh.chillteam.security;

import iuh.chillteam.entity.User;
import iuh.chillteam.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;

/**
 * UserDetailsService implementation for Spring Security
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.debug("Loading user by email: {}", email);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        // Check if user is deleted
        if (user.getDeletedAt() != null) {
            throw new UsernameNotFoundException("User account has been deleted");
        }

        // Check if user is active
        if (!user.getIsActive()) {
            log.warn("User account is inactive: {}", email);
            throw new UsernameNotFoundException("User account is inactive");
        }

        return new CustomUserDetails(user);
    }

    /**
     * Custom UserDetails implementation
     */
    public static class CustomUserDetails implements UserDetails {

        private final User user;

        public CustomUserDetails(User user) {
            this.user = user;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return Collections.singletonList(
                    new SimpleGrantedAuthority("ROLE_" + user.getRole().name())
            );
        }

        @Override
        public String getPassword() {
            return user.getPassword();
        }

        @Override
        public String getUsername() {
            return user.getEmail();
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return user.getIsActive();
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return user.getIsActive() && user.getDeletedAt() == null;
        }

        /**
         * Get the underlying User entity
         */
        public User getUser() {
            return user;
        }

        /**
         * Get user ID
         */
        public Long getUserId() {
            return user.getId();
        }

        /**
         * Get user role
         */
        public User.Role getRole() {
            return user.getRole();
        }
    }
}
