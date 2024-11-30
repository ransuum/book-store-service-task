package com.epam.rd.autocode.spring.project.controller.withoutSite;

import com.epam.rd.autocode.spring.project.model.enums.AuthoritiesType;
import com.epam.rd.autocode.spring.project.model.request.login.LoginRequest;
import com.epam.rd.autocode.spring.project.model.request.register.ClientReq;
import com.epam.rd.autocode.spring.project.model.request.register.EmployeeReq;
import com.epam.rd.autocode.spring.project.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
public class AuthController {
    private final AuthService authService;
    private final AuthenticationManager authenticationManager;

    public AuthController(AuthService authService, AuthenticationManager authenticationManager) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register/employee")
    public ResponseEntity<EmployeeReq> registerEmployee(@RequestBody @Valid EmployeeReq employeeReq) {
        return ResponseEntity.ok(authService.register(employeeReq, AuthoritiesType.EMPLOYEE));
    }

    @PostMapping("/register/client")
    public ResponseEntity<ClientReq> registerClient(@RequestBody ClientReq clientReq) {
        return ResponseEntity.ok(authService.register(clientReq, AuthoritiesType.CLIENT));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody @Valid LoginRequest loginReq) {
        return ResponseEntity.ok(authService.login(loginReq, authenticationManager));
    }

    @PostMapping("/logout/acc")
    public ResponseEntity<?> logout(HttpServletResponse httpServletResponse) {
        authService.logout(httpServletResponse);
        return ResponseEntity.ok("Logged out successfully");
    }
}
