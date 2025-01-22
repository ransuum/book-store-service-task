package com.epam.rd.autocode.spring.project.controller.thymeleaf;

import com.epam.rd.autocode.spring.project.model.request.edit.EmployeeUpdateRequest;
import com.epam.rd.autocode.spring.project.util.edit_manager.config.EditConfig;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employee")
public class EmployeeEditController {
    private final EditConfig editConfig;

    public EmployeeEditController(EditConfig editConfig) {
        this.editConfig = editConfig;
    }

    @GetMapping("/form")
    public String getForm(Model model){
        model.addAttribute("employee", new EmployeeUpdateRequest());
        return "employee-edit";
    }

    @PostMapping("/edit")
    public String employee(@ModelAttribute("employee") EmployeeUpdateRequest employeeUpdateRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        editConfig.employeeUpdate().edit(authentication.getName(), employeeUpdateRequest);
        return "redirect:/home";
    }
}
