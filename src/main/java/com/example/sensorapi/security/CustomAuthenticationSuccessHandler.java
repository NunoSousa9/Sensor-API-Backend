package com.example.sensorapi.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final TokenUtil tokenUtil;

    public CustomAuthenticationSuccessHandler(TokenUtil tokenUtil) {
        this.tokenUtil = tokenUtil;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        String token = tokenUtil.generateToken(authentication.getName());

        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);

        response.setContentType("application/json");
        new ObjectMapper().writeValue(response.getWriter(), tokenMap);
        response.sendRedirect("/sensors");
    }
}
