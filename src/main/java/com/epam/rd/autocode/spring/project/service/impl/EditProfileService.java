package com.epam.rd.autocode.spring.project.service.impl;

import com.epam.rd.autocode.spring.project.model.enums.AuthoritiesType;
import com.epam.rd.autocode.spring.project.model.request.edit.ClientUpdateRequest;
import com.epam.rd.autocode.spring.project.model.request.edit.EmployeeUpdateRequest;
import com.epam.rd.autocode.spring.project.model.request.edit.UserUpdateRequest;
import com.epam.rd.autocode.spring.project.util.edit_manager.Edit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EditProfileService {
    private final Map<AuthoritiesType, Edit<? extends UserUpdateRequest>> editStrategies;

    public EditProfileService(List<Edit<? extends UserUpdateRequest>> edits) {
        this.editStrategies = edits.stream()
                .collect(Collectors.toMap(Edit::getAuthoritiesType, Function.identity()));
    }

    public UserUpdateRequest editProfileEmployee(EmployeeUpdateRequest userUpdateRequest, AuthoritiesType authoritiesType) {
        Edit<UserUpdateRequest> editStrategy = (Edit<UserUpdateRequest>) editStrategies.get(authoritiesType);
        return editStrategy.edit(userUpdateRequest);
    }

    public UserUpdateRequest editProfileClient(ClientUpdateRequest clientUpdateRequest, AuthoritiesType authoritiesType) {
        Edit<ClientUpdateRequest> editStrategy = (Edit<ClientUpdateRequest>) editStrategies.get(authoritiesType);
        return editStrategy.edit(clientUpdateRequest);
    }
}
