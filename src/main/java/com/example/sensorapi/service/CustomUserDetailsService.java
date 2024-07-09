package com.example.sensorapi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.sensorapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.debug("Attempting to load user by username: {}", username);

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
