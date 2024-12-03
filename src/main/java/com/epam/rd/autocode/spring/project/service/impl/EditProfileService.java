package com.epam.rd.autocode.spring.project.service.impl;

import com.epam.rd.autocode.spring.project.model.enums.AuthoritiesType;
import com.epam.rd.autocode.spring.project.model.request.edit.UserUpdateRequest;
import com.epam.rd.autocode.spring.project.util.edit_manager.Edit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EditProfileService {
    private final Map<AuthoritiesType, Edit> authoritiesTypeEditMap;

    public EditProfileService(List<Edit> edits) {
        this.authoritiesTypeEditMap = edits.stream().collect(Collectors.toMap(Edit::getAuthoritiesType, e -> e));
    }

    public UserUpdateRequest editProfile(UserUpdateRequest userUpdateRequest, AuthoritiesType authoritiesType) {
        return authoritiesTypeEditMap.get(authoritiesType).edit(userUpdateRequest);
    }
}
