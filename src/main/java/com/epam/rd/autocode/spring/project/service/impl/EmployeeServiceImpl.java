package com.epam.rd.autocode.spring.project.service.impl;

import com.epam.rd.autocode.spring.project.dto.EmployeeDTO;
import com.epam.rd.autocode.spring.project.exception.AlreadyExistException;
import com.epam.rd.autocode.spring.project.exception.NotFoundException;
import com.epam.rd.autocode.spring.project.model.Employee;
import com.epam.rd.autocode.spring.project.repo.EmployeeRepository;
import com.epam.rd.autocode.spring.project.service.EmployeeService;
import com.epam.rd.autocode.spring.project.util.checking_validator.Validator;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;
    private final Validator<Employee, EmployeeDTO> validator;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper modelMapper,
                               Validator<Employee, EmployeeDTO> validator) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;
    }

    @Override
    public Page<EmployeeDTO> getAllEmployees(Pageable pageable) {
        return employeeRepository.findAll(pageable)
                .map(employee -> modelMapper.map(employee, EmployeeDTO.class));
    }

    @Override
    public EmployeeDTO getEmployeeByEmail(String email) {
        return modelMapper.map(employeeRepository.findByEmail(email)
                .orElseThrow(()
                        -> new NotFoundException("Employee with this email " + email + " not found")), EmployeeDTO.class);
    }

    @Override
    public EmployeeDTO updateEmployeeByEmail(String email, EmployeeDTO employee) {
        Employee employee1 = employeeRepository.findByEmail(email)
                .orElseThrow(()
                        -> new NotFoundException("Employee with this email " + email + " not found"));
        return modelMapper.map(employeeRepository.save(validator.validate(employee1, employee)), EmployeeDTO.class);
    }

    @Override
    public void deleteEmployeeByEmail(String email) {
        employeeRepository.delete(employeeRepository.findByEmail(email)
                .orElseThrow(()
                        -> new NotFoundException("Employee with this email " + email + " not found")));
    }

    @Override
    public EmployeeDTO addEmployee(EmployeeDTO employee) {
        employeeRepository.findByEmail(employee.getEmail()).ifPresent(employee1 -> {
            throw new AlreadyExistException("Employee with this email " + employee.getEmail() + " already exist");
        });

        Employee employee1 = new Employee();
        employee1.setName(employee.getName());
        employee1.setEmail(employee.getEmail());
        employee1.setPhone(employee.getPhone());
        employee1.setBirthDate(employee.getBirthDate());
        employee1.setEmail(employee.getEmail());
        employee1.setPassword(employee.getPassword());

        return modelMapper.map(employee1, EmployeeDTO.class);
    }
}
