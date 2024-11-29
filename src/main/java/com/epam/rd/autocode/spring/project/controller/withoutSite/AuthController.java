package com.epam.rd.autocode.spring.project.controller.withoutSite;

import com.epam.rd.autocode.spring.project.dto.ClientDTO;
import com.epam.rd.autocode.spring.project.dto.EmployeeDTO;
import com.epam.rd.autocode.spring.project.exception.NotFoundException;
import com.epam.rd.autocode.spring.project.model.Client;
import com.epam.rd.autocode.spring.project.model.enums.AuthoritiesType;
import com.epam.rd.autocode.spring.project.model.request.login.LoginRequest;
import com.epam.rd.autocode.spring.project.model.request.register.ClientReq;
import com.epam.rd.autocode.spring.project.model.request.register.EmployeeReq;
import com.epam.rd.autocode.spring.project.repo.ClientRepository;
import com.epam.rd.autocode.spring.project.security.TokenProvider;
import com.epam.rd.autocode.spring.project.service.AuthService;
import com.epam.rd.autocode.spring.project.util.auth_management.auth.AuthManager;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class AuthController {
    private final AuthService authService;
    private final TokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;
    private final Map<AuthoritiesType, AuthManager> authManagers;
    private final ClientRepository clientRepository;

    public AuthController(AuthService authService, TokenProvider tokenProvider, AuthenticationManager authenticationManager,
                          List<AuthManager> authManagers, ClientRepository clientRepository) {
        this.authService = authService;
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
        this.authManagers = authManagers.stream()
                .collect(Collectors.toMap(AuthManager::getAuthoritiesType, a -> a));
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

    @PostMapping("/login/client")
    public ResponseEntity<LoginRequest> loginClient(@RequestBody @Valid LoginRequest loginRequest, HttpServletResponse httpServletResponse) {
        return ResponseEntity.ok(authManagers.get(AuthoritiesType.CLIENT).login(loginRequest, authenticationManager, tokenProvider, httpServletResponse));
    }

    @PostMapping("/login/employee")
    public ResponseEntity<LoginRequest> loginEmployee(@RequestBody @Valid LoginRequest loginRequest, HttpServletResponse httpServletResponse) {
        return ResponseEntity.ok(authManagers.get(AuthoritiesType.EMPLOYEE).login(loginRequest, authenticationManager, tokenProvider, httpServletResponse));
    }
}
