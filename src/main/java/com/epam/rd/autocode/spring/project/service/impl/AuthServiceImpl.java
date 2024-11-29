package com.epam.rd.autocode.spring.project.service.impl;

import com.epam.rd.autocode.spring.project.model.enums.AuthoritiesType;
import com.epam.rd.autocode.spring.project.model.request.register.UserReq;
import com.epam.rd.autocode.spring.project.service.AuthService;
import com.epam.rd.autocode.spring.project.util.auth_management.register.RegisterManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {
    private final Map<AuthoritiesType, RegisterManager> authManagers;

    public AuthServiceImpl(List<RegisterManager> registerManagers) {
        this.authManagers = registerManagers.stream()
                .collect(Collectors.toMap(RegisterManager::getAuthoritiesType, registerManager -> registerManager));
    }

    @Override
    public <T extends UserReq> T register(T userReq, AuthoritiesType authoritiesType) {
        return authManagers.get(authoritiesType).register(userReq);
    }
}
