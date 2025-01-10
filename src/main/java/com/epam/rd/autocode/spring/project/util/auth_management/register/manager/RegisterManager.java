package com.epam.rd.autocode.spring.project.util.auth_management.register.manager;

import com.epam.rd.autocode.spring.project.model.enums.AuthoritiesType;

public interface RegisterManager<T, D> {
    D register(T user);
}
