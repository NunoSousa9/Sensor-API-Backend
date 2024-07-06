package com.example.sensorapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.logging.Logger;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    private static final Logger LOGGER = Logger.getLogger(CustomUserDetailsService.class.getName());

    @Autowired
    public CustomUserDetailsService(@Lazy PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOGGER.info("Attempting to load user by username: " + username);

        if ("user".equals(username)) {
            String encodedPassword = passwordEncoder.encode("pass");
            LOGGER.info("User found: " + username + " with encoded password: " + encodedPassword);

            return new User(username, encodedPassword, Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
        }

        LOGGER.warning("User not found: " + username);
        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}
