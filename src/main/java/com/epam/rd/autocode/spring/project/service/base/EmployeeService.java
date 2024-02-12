package com.epam.rd.autocode.spring.project.service.base;

import com.epam.rd.autocode.spring.project.controller.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {

    List<EmployeeDTO> getAllEmployees();

    EmployeeDTO getEmployeeById(long id);

    EmployeeDTO updateEmployeeById(long id, EmployeeDTO employee);

    void deleteEmployeeById(long id);

    EmployeeDTO addEmployee(EmployeeDTO employee);
}
