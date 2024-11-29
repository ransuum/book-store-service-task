package com.epam.rd.autocode.spring.project.util.auth_management.auth;

import com.epam.rd.autocode.spring.project.model.enums.AuthoritiesType;
import com.epam.rd.autocode.spring.project.model.request.login.LoginRequest;
import com.epam.rd.autocode.spring.project.security.TokenProvider;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;

public interface AuthManager {
    LoginRequest login(LoginRequest loginRequest, AuthenticationManager authenticationManager,
                       TokenProvider tokenProvider, HttpServletResponse httpServletResponse);
    AuthoritiesType getAuthoritiesType();
}
