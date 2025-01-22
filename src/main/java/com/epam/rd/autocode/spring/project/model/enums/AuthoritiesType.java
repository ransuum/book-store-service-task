package com.epam.rd.autocode.spring.project.model.enums;

import lombok.Getter;

@Getter
public enum AuthoritiesType {
    EMPLOYEE("ROLE_EMPLOYEE"),
    CLIENT("ROLE_CLIENT");

    private final String value;

    AuthoritiesType(String value) {
        this.value = value;
    }
}
