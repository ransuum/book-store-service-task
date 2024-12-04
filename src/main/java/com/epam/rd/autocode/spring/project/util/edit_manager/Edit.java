package com.epam.rd.autocode.spring.project.util.edit_manager;

import com.epam.rd.autocode.spring.project.model.enums.AuthoritiesType;
import com.epam.rd.autocode.spring.project.model.request.edit.UserUpdateRequest;

public interface Edit<T extends UserUpdateRequest> {
    T edit(String email, T userUpdateRequest);
    AuthoritiesType getAuthoritiesType();
}
