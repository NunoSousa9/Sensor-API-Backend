package com.example.sensorapi.controller;

import com.example.sensorapi.model.AuthRequest;
import com.example.sensorapi.model.AuthResponse;
import com.example.sensorapi.security.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import java.util.logging.Logger;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger LOGGER = Logger.getLogger(AuthController.class.getName());


    private final AuthenticationManager authenticationManager;
    private final TokenUtil tokenUtil;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, TokenUtil tokenUtil) {
        this.authenticationManager = authenticationManager;
        this.tokenUtil = tokenUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
            String token = tokenUtil.generateToken(authentication.getName());
            LOGGER.info("Token generated for username: " + authRequest.getUsername());

            return ResponseEntity.ok(new AuthResponse(token));
        } catch (AuthenticationException e) {
            LOGGER.warning("Authentication failed for username: " + authRequest.getUsername());
            return ResponseEntity.status(401).body(new AuthResponse(null));
        }
    }
}