package com.epam.rd.autocode.spring.project.service;

import com.epam.rd.autocode.spring.project.model.request.login.LoginRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;

import java.util.Map;

public interface AuthService {
    Map<String, Object> login(LoginRequest loginReq, AuthenticationManager authenticationManager);
    void logout(HttpServletResponse response);
}
