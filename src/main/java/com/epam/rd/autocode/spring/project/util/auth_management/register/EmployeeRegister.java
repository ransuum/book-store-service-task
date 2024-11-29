package com.epam.rd.autocode.spring.project.util.auth_management.register;

import com.epam.rd.autocode.spring.project.model.Employee;
import com.epam.rd.autocode.spring.project.model.enums.AuthoritiesType;
import com.epam.rd.autocode.spring.project.model.request.register.EmployeeReq;
import com.epam.rd.autocode.spring.project.model.request.register.UserReq;
import com.epam.rd.autocode.spring.project.repo.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployeeRegister implements RegisterManager {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    @Override
    public <T extends UserReq> T register(T user) {
        EmployeeReq employee = modelMapper.map(user, EmployeeReq.class);
        employeeRepository.save(new Employee(employee.getEmail(),
                new BCryptPasswordEncoder().encode(employee.getPassword()),
                employee.getName(), employee.getBirthDate(), employee.getPhone()));
        return (T) employee;
    }

    @Override
    public AuthoritiesType getAuthoritiesType() {
        return AuthoritiesType.EMPLOYEE;
    }
}
