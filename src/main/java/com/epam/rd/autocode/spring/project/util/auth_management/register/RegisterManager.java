package com.epam.rd.autocode.spring.project.util.auth_management.register;

import com.epam.rd.autocode.spring.project.model.enums.AuthoritiesType;
import com.epam.rd.autocode.spring.project.model.request.register.UserReq;

public interface RegisterManager {
    <T extends UserReq> T register(T user);
    AuthoritiesType getAuthoritiesType();
}
