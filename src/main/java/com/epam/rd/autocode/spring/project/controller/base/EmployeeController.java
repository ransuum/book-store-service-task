package com.epam.rd.autocode.spring.project.controller.base;

import com.epam.rd.autocode.spring.project.controller.dto.EmployeeDTO;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

// TODO Modify code here

public interface EmployeeController {

    ModelAndView getEmployeeById(Long id, Model model);

    ModelAndView showEmployeeAddForm(Model model);

    ModelAndView addEmployee(EmployeeDTO employeeDTO, BindingResult bindingResult, Model model);

    ModelAndView getAllEmployees(Model model);

    ModelAndView showEmployeeEditForm(Long id, Model model);

    ModelAndView editEmployeeById(Long id, EmployeeDTO employeeDTO, BindingResult bindingResult, Model model);

    ModelAndView deleteEmployeeById(Long id);
}
