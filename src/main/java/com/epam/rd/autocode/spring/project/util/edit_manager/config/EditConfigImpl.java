package com.epam.rd.autocode.spring.project.util.edit_manager.config;

import com.epam.rd.autocode.spring.project.dto.ClientDTO;
import com.epam.rd.autocode.spring.project.dto.EmployeeDTO;
import com.epam.rd.autocode.spring.project.model.request.edit.ClientUpdateRequest;
import com.epam.rd.autocode.spring.project.model.request.edit.EmployeeUpdateRequest;
import com.epam.rd.autocode.spring.project.util.edit_manager.Edit;
import org.springframework.stereotype.Component;

@Component
public class EditConfigImpl implements EditConfig {
    private final Edit<ClientUpdateRequest, ClientDTO> editClient;
    private final Edit<EmployeeUpdateRequest, EmployeeDTO> editEmployee;

    public EditConfigImpl(Edit<ClientUpdateRequest, ClientDTO> editClient, Edit<EmployeeUpdateRequest, EmployeeDTO> editEmployee) {
        this.editClient = editClient;
        this.editEmployee = editEmployee;
    }

    @Override
    public Edit<ClientUpdateRequest, ClientDTO> clientUpdate() {
        return editClient;
    }

    @Override
    public Edit<EmployeeUpdateRequest, EmployeeDTO> employeeUpdate() {
        return editEmployee;
    }
}
