package com.epam.rd.autocode.spring.project.controller.thymeleaf;

import com.epam.rd.autocode.spring.project.model.request.login.LoginRequest;
import com.epam.rd.autocode.spring.project.model.request.register.ClientReq;
import com.epam.rd.autocode.spring.project.model.request.register.EmployeeReq;
import com.epam.rd.autocode.spring.project.service.AuthService;
import com.epam.rd.autocode.spring.project.util.auth_management.register.chooser.RegisterChooser;
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
    private final RegisterChooser registerChooser;

    public AccountController(AuthService authService, AuthenticationManager authenticationManager, RegisterChooser registerChooser) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
        this.registerChooser = registerChooser;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String registration(Model model) {
        model.addAttribute("clientReq", new ClientReq());
        model.addAttribute("employeeReq", new EmployeeReq());
        return "register";
    }

    @PostMapping("/thyme/register/client")
    public String registerClient(@ModelAttribute ClientReq clientReq) {
        registerChooser.clientRegisterManager().register(clientReq);
        return "redirect:/home";
    }

    @PostMapping("/thyme/register/employee")
    public String registerEmployee(@ModelAttribute EmployeeReq employeeReq) {
        registerChooser.employeeRegisterManager().register(employeeReq);
        return "redirect:/home";
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
