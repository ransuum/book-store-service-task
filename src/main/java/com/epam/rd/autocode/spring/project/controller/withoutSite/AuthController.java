package com.epam.rd.autocode.spring.project.controller.withoutSite;

import com.epam.rd.autocode.spring.project.model.enums.AuthoritiesType;
import com.epam.rd.autocode.spring.project.model.request.login.LoginRequest;
import com.epam.rd.autocode.spring.project.model.request.register.ClientReq;
import com.epam.rd.autocode.spring.project.model.request.register.EmployeeReq;
import com.epam.rd.autocode.spring.project.repo.ClientRepository;
import com.epam.rd.autocode.spring.project.security.TokenProvider;
import com.epam.rd.autocode.spring.project.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class AuthController {
    private final AuthService authService;
    private final TokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;
    private final ClientRepository clientRepository;

    public AuthController(AuthService authService, TokenProvider tokenProvider,
                          AuthenticationManager authenticationManager, ClientRepository clientRepository) {
        this.authService = authService;
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
        this.clientRepository = clientRepository;
    }

    @PostMapping("/register/employee")
    public ResponseEntity<EmployeeReq> registerEmployee(@RequestBody @Valid EmployeeReq employeeReq) {
        return ResponseEntity.ok(authService.register(employeeReq, AuthoritiesType.EMPLOYEE));
    }

    @PostMapping("/register/client")
    public ResponseEntity<ClientReq> registerClient(@RequestBody ClientReq clientReq) {
        return ResponseEntity.ok(authService.register(clientReq, AuthoritiesType.CLIENT));
    }
}
