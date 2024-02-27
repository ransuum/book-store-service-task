package com.epam.rd.autocode.spring.project.service.base;

import com.epam.rd.autocode.spring.project.controller.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {

    List<EmployeeDTO> getAllEmployees();

    EmployeeDTO getEmployeeById(Long id);

    EmployeeDTO updateEmployeeById(Long id, EmployeeDTO employee);

    void deleteEmployeeById(Long id);

    EmployeeDTO addEmployee(EmployeeDTO employee);
}
