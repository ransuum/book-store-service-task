package com.epam.rd.autocode.spring.project.controller.thymeleaf;

import com.epam.rd.autocode.spring.project.model.request.edit.ClientUpdateRequest;
import com.epam.rd.autocode.spring.project.model.request.edit.EmployeeUpdateRequest;
import com.epam.rd.autocode.spring.project.model.request.login.LoginRequest;
import com.epam.rd.autocode.spring.project.service.AuthService;
import com.epam.rd.autocode.spring.project.util.edit_manager.config.EditConfig;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class AccountController {
    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final EditConfig editConfig;

    public AccountController(AuthService authService, AuthenticationManager authenticationManager, EditConfig editConfig) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
        this.editConfig = editConfig;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String registration() {
        return "register";
    }

    @PostMapping("/sign-in")
    public String signIn(@ModelAttribute LoginRequest loginReq) {
        authService.login(loginReq, authenticationManager);
        return "redirect:/home";
    }

    @GetMapping("/sign-out/acc")
    public String logout(HttpServletResponse response) {
        authService.logout(response);
        return "redirect:/home";
    }
}
