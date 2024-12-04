package com.epam.rd.autocode.spring.project.service.impl;

import com.epam.rd.autocode.spring.project.model.request.edit.ClientUpdateRequest;
import com.epam.rd.autocode.spring.project.model.request.edit.EmployeeUpdateRequest;
import com.epam.rd.autocode.spring.project.model.request.edit.UserUpdateRequest;
import com.epam.rd.autocode.spring.project.util.edit_manager.config.EditConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EditProfileService {
    private final EditConfig editConfig;

    public EditProfileService(EditConfig editConfig) {
        this.editConfig = editConfig;
    }

    public UserUpdateRequest editProfileEmployee(EmployeeUpdateRequest userUpdateRequest) {
        return editConfig.employeeUpdate().edit(userUpdateRequest);
    }

    public UserUpdateRequest editProfileClient(ClientUpdateRequest clientUpdateRequest) {
        return editConfig.clientUpdate().edit(clientUpdateRequest);
    }
}
