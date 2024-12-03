package com.epam.rd.autocode.spring.project.util.edit_manager;

import com.epam.rd.autocode.spring.project.model.enums.AuthoritiesType;
import com.epam.rd.autocode.spring.project.model.request.edit.EmployeeUpdateRequest;
import com.epam.rd.autocode.spring.project.repo.EmployeeRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class EditEmployee implements Edit<EmployeeUpdateRequest> {
    private final EmployeeRepository employeeRepository;

    public EditEmployee(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeUpdateRequest edit(EmployeeUpdateRequest employeeUpdateRequest) {
        employeeRepository.findByEmail(employeeUpdateRequest.getEmail()).ifPresent(employee -> {
            if (employeeUpdateRequest.getBirthdate() != null) employee.setBirthDate(employeeUpdateRequest.getBirthdate());
            if (employeeUpdateRequest.getPhone() != null) employee.setPhone(employeeUpdateRequest.getPhone());
            if (employeeUpdateRequest.getName() != null) employee.setName(employeeUpdateRequest.getName());
            if (employeeUpdateRequest.getPassword() != null)
                employee.setPassword(new BCryptPasswordEncoder().encode(employeeUpdateRequest.getPassword()));
            employeeRepository.save(employee);
        });
        return employeeUpdateRequest;
    }

    @Override
    public AuthoritiesType getAuthoritiesType() {
        return AuthoritiesType.EMPLOYEE;
    }
}
