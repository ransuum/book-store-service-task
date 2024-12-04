package com.epam.rd.autocode.spring.project.util.edit_manager.config;

import com.epam.rd.autocode.spring.project.model.request.edit.ClientUpdateRequest;
import com.epam.rd.autocode.spring.project.model.request.edit.EmployeeUpdateRequest;
import com.epam.rd.autocode.spring.project.repo.ClientRepository;
import com.epam.rd.autocode.spring.project.repo.EmployeeRepository;
import com.epam.rd.autocode.spring.project.util.edit_manager.Edit;
import com.epam.rd.autocode.spring.project.util.edit_manager.EditClient;
import com.epam.rd.autocode.spring.project.util.edit_manager.EditEmployee;
import org.springframework.stereotype.Component;

@Component
public class EditConfigImpl implements EditConfig {
    private final ClientRepository clientRepository;
    private final EmployeeRepository employeeRepository;

    public EditConfigImpl(ClientRepository clientRepository, EmployeeRepository employeeRepository) {
        this.clientRepository = clientRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Edit<ClientUpdateRequest> clientUpdate() {
        return new EditClient(clientRepository);
    }

    @Override
    public Edit<EmployeeUpdateRequest> employeeUpdate() {
        return new EditEmployee(employeeRepository);
    }
}
