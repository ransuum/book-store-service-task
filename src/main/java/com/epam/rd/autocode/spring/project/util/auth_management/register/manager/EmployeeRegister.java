package com.epam.rd.autocode.spring.project.util.auth_management.register.manager;

import com.epam.rd.autocode.spring.project.dto.EmployeeDTO;
import com.epam.rd.autocode.spring.project.exception.AlreadyExistException;
import com.epam.rd.autocode.spring.project.model.Employee;
import com.epam.rd.autocode.spring.project.model.enums.AuthoritiesType;
import com.epam.rd.autocode.spring.project.model.request.register.EmployeeReq;
import com.epam.rd.autocode.spring.project.repo.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployeeRegister implements RegisterManager<EmployeeReq, EmployeeDTO> {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    @Override
    public EmployeeDTO register(EmployeeReq user) {
        employeeRepository.findByEmail(user.getEmail()).ifPresent(employee1 -> {
            throw new AlreadyExistException("You can't use this employee email");
        });

        Employee employee = employeeRepository.save(
                new Employee(user.getEmail(),
                new BCryptPasswordEncoder().encode(user.getPassword()),
                user.getName(), user.getBirthdate(), user.getPhone())
        );
        return modelMapper.map(employee, EmployeeDTO.class);
    }

    @Override
    public AuthoritiesType getAuthoritiesType() {
        return AuthoritiesType.EMPLOYEE;
    }
}
