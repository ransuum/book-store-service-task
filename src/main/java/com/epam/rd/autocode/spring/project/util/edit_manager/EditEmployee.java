package com.epam.rd.autocode.spring.project.util.edit_manager;

import com.epam.rd.autocode.spring.project.dto.EmployeeDTO;
import com.epam.rd.autocode.spring.project.model.Employee;
import com.epam.rd.autocode.spring.project.model.request.edit.EmployeeUpdateRequest;
import com.epam.rd.autocode.spring.project.repo.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EditEmployee implements Edit<EmployeeUpdateRequest, EmployeeDTO> {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EditEmployee(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public EmployeeDTO edit(String email, EmployeeUpdateRequest employeeUpdateRequest) {
        employeeRepository.findByEmail(email).ifPresent(employee -> {
            if (employeeUpdateRequest.getBirthdate() != null)
                employee.setBirthDate(employeeUpdateRequest.getBirthdate());
            if (!employeeUpdateRequest.getPhone().trim().isEmpty() && !employeeUpdateRequest.getPhone().trim().isBlank())
                employee.setPhone(employeeUpdateRequest.getPhone());
            if (employeeUpdateRequest.getName() != null) employee.setName(employeeUpdateRequest.getName());
            if (employeeUpdateRequest.getPassword() != null)
                employee.setPassword(new BCryptPasswordEncoder().encode(employeeUpdateRequest.getPassword()));
            Employee employee1 = employeeRepository.save(employee);
            log.info("employee: {}", employee1);
        });

        return modelMapper.map(employeeUpdateRequest, EmployeeDTO.class);
    }
}
