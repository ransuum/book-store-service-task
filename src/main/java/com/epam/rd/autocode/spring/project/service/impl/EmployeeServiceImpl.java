package com.epam.rd.autocode.spring.project.service.impl;

import com.epam.rd.autocode.spring.project.dto.EmployeeDTO;
import com.epam.rd.autocode.spring.project.exception.AlreadyExistException;
import com.epam.rd.autocode.spring.project.exception.NotFoundException;
import com.epam.rd.autocode.spring.project.model.Employee;
import com.epam.rd.autocode.spring.project.repo.EmployeeRepository;
import com.epam.rd.autocode.spring.project.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
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

        if (employee.getName() != null && !employee.getName().equals(employee1.getName())) employee1.setName(employee.getName());
        if (employee.getEmail() != null && !employee.getEmail().equals(employee1.getEmail())) employee1.setEmail(employee.getEmail());
        if (employee.getPhone() != null && !employee.getPhone().equals(employee1.getPhone())) employee1.setPhone(employee.getPhone());
        if (employee.getBirthDate() != null) employee1.setBirthDate(employee.getBirthDate());

        return modelMapper.map(employeeRepository.save(employee1), EmployeeDTO.class);
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
        employee1.setPassword(new BCryptPasswordEncoder().encode(employee.getPassword()));

        return modelMapper.map(employee1, EmployeeDTO.class);
    }
}
