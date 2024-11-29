package com.epam.rd.autocode.spring.project.service;

import com.epam.rd.autocode.spring.project.model.enums.AuthoritiesType;
import com.epam.rd.autocode.spring.project.model.request.register.UserReq;

public interface AuthService {
    <T extends UserReq> T register(T userReq, AuthoritiesType authoritiesType);
}
