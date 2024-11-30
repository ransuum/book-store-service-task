package com.epam.rd.autocode.spring.project.service;

import com.epam.rd.autocode.spring.project.model.enums.AuthoritiesType;
import com.epam.rd.autocode.spring.project.model.request.login.LoginRequest;
import com.epam.rd.autocode.spring.project.model.request.register.UserReq;
import com.epam.rd.autocode.spring.project.security.TokenProvider;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;

import java.util.Map;

public interface AuthService {
    <T extends UserReq> T register(T userReq, AuthoritiesType authoritiesType);
    Map<String, Object> login(LoginRequest loginReq, AuthenticationManager authenticationManager);
    void logout(HttpServletResponse response);
}
