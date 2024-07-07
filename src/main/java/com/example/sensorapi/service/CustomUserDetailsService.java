package com.example.sensorapi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.sensorapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final String encodedPassword;

    @Autowired
    public CustomUserDetailsService(@Lazy PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.encodedPassword = passwordEncoder.encode("pass");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.debug("Attempting to load user by username: {}", username);

        if ("user".equals(username)) {
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            logger.debug("Hardcoded user found: {} with encoded password: {}", username, encodedPassword);
            return new User(username, encodedPassword, authorities);
        }

        com.example.sensorapi.model.User user = userRepository.findByUsername(username);

        if (user != null) {
            logger.debug("User found: {} with encoded password: {}", user.getUsername(), user.getPassword());
            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    user.getPassword(),
                    AuthorityUtils.createAuthorityList(user.getRoles().toArray(new String[0]))
            );
        }
        logger.warn("User not found with username: {}", username);
        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}
