package com.epam.rd.autocode.spring.project.util.edit_manager.config;

import com.epam.rd.autocode.spring.project.model.request.edit.ClientUpdateRequest;
import com.epam.rd.autocode.spring.project.model.request.edit.EmployeeUpdateRequest;
import com.epam.rd.autocode.spring.project.util.edit_manager.Edit;

public interface EditConfig {
    Edit<ClientUpdateRequest> clientUpdate();
    Edit<EmployeeUpdateRequest> employeeUpdate();
}
