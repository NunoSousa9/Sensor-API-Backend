package com.example.sensorapi.security;

import com.example.sensorapi.model.User;
import com.example.sensorapi.repository.UserRepository;
import com.example.sensorapi.service.CustomUserDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
public class AuthProviderTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private TokenUtil tokenUtil;

    @InjectMocks
    private CustomUserDetailsService userDetailsService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLoadUserByUsername_Success() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password");
        user.setRoles(Collections.emptyList());

        when(userRepository.findByUsername("testuser")).thenReturn(user);

        UserDetails userDetails = userDetailsService.loadUserByUsername("testuser");

        assertThat(userDetails).isNotNull();
        assertThat(userDetails.getUsername()).isEqualTo("testuser");
    }

    @Test
    public void testLoadUserByUsername_Failure() {
        when(userRepository.findByUsername("unknown")).thenReturn(null);

        assertThatThrownBy(() -> userDetailsService.loadUserByUsername("unknown"))
                .isInstanceOf(UsernameNotFoundException.class)
                .hasMessageContaining("User not found with username: unknown");
    }

    @Test
    public void testGenerateAndValidateToken() {
        String username = "testuser";
        String token = "mockToken";

        when(tokenUtil.generateToken(username)).thenReturn(token);
        when(tokenUtil.validateToken(token)).thenReturn(true);

        String generatedToken = tokenUtil.generateToken(username);

        assertThat(generatedToken).isNotNull();
        assertThat(tokenUtil.validateToken(generatedToken)).isTrue();
    }
}
