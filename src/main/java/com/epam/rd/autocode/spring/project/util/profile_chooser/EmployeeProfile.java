package com.epam.rd.autocode.spring.project.util.profile_chooser;

import com.epam.rd.autocode.spring.project.dto.EmployeeDTO;
import com.epam.rd.autocode.spring.project.dto.OrderDTO;
import com.epam.rd.autocode.spring.project.model.enums.AuthoritiesType;
import com.epam.rd.autocode.spring.project.service.EmployeeService;
import com.epam.rd.autocode.spring.project.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.List;

@Component
public class EmployeeProfile implements Profile {
    private final EmployeeService employeeService;
    private final OrderService orderService;

    public EmployeeProfile(EmployeeService employeeService, OrderService orderService) {
        this.employeeService = employeeService;
        this.orderService = orderService;
    }

    @Override
    public String showProfile(String email, List<String> roles, Model model) {
        EmployeeDTO employeeDTO = employeeService.getEmployeeByEmail(email);
        model.addAttribute("employee", employeeDTO);
        Page<OrderDTO> ordersByEmployee = orderService.getOrdersByEmployee(
                employeeDTO.getEmail(),
                PageRequest.of(page, size)
        );
        model.addAttribute("ordersEmployee", ordersByEmployee);
        return "employee-profile";
    }

    @Override
    public Boolean getName() {
        return Boolean.TRUE;
    }
}
