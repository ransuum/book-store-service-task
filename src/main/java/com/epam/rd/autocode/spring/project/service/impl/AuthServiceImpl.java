package com.epam.rd.autocode.spring.project.service.impl;

import com.epam.rd.autocode.spring.project.model.request.login.LoginRequest;
import com.epam.rd.autocode.spring.project.service.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public Map<String, Object> login(LoginRequest loginReq, AuthenticationManager authenticationManager) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(loginReq.getEmail(), loginReq.getPassword());
        Authentication authenticated = authenticationManager.authenticate(usernamePassword);
        return Map.of(
                "role", authenticated.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()),
                "email", usernamePassword.getName()
        );
    }

    @Override
    public void logout(HttpServletResponse response) {
        Cookie jwtCookie = new Cookie("Authorization", null);
        jwtCookie.setHttpOnly(true);
        jwtCookie.setSecure(true);
        jwtCookie.setMaxAge(0);
        jwtCookie.setPath("/");
        response.addCookie(jwtCookie);
    }
}
