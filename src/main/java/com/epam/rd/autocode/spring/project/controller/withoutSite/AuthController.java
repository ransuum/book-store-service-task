package com.epam.rd.autocode.spring.project.controller.withoutSite;

import com.epam.rd.autocode.spring.project.dto.ClientDTO;
import com.epam.rd.autocode.spring.project.dto.EmployeeDTO;
import com.epam.rd.autocode.spring.project.model.request.login.LoginRequest;
import com.epam.rd.autocode.spring.project.model.request.register.ClientReq;
import com.epam.rd.autocode.spring.project.model.request.register.EmployeeReq;
import com.epam.rd.autocode.spring.project.service.AuthService;
import com.epam.rd.autocode.spring.project.util.auth_management.register.chooser.RegisterChooser;
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
    private final RegisterChooser registerChooser;

    public AuthController(AuthService authService, AuthenticationManager authenticationManager, RegisterChooser registerChooser) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
        this.registerChooser = registerChooser;
    }

    @PostMapping("/register/employee")
    public ResponseEntity<EmployeeDTO> registerEmployee(@RequestBody @Valid EmployeeReq userReq) {
        return ResponseEntity.ok(registerChooser.employeeRegisterManager().register(userReq));
    }

    @PostMapping("/register/client")
    public ResponseEntity<ClientDTO> registerEmployee(@RequestBody @Valid ClientReq userReq) {
        return ResponseEntity.ok(registerChooser.clientRegisterManager().register(userReq));
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
