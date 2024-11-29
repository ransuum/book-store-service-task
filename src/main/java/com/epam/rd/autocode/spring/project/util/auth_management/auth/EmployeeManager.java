package com.epam.rd.autocode.spring.project.util.auth_management.auth;

import com.epam.rd.autocode.spring.project.exception.NotFoundException;
import com.epam.rd.autocode.spring.project.model.Employee;
import com.epam.rd.autocode.spring.project.model.enums.AuthoritiesType;
import com.epam.rd.autocode.spring.project.model.request.login.LoginRequest;
import com.epam.rd.autocode.spring.project.repo.EmployeeRepository;
import com.epam.rd.autocode.spring.project.security.TokenProvider;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class EmployeeManager implements AuthManager, UserDetailsService {
    private final EmployeeRepository employeeRepository;

    public EmployeeManager(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public LoginRequest login(LoginRequest loginRequest, AuthenticationManager authenticationManager,
                              TokenProvider tokenProvider, HttpServletResponse httpServletResponse) {
        Employee users = employeeRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(()-> new NotFoundException("user with this email not found"));

        var usernamePassword = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
        var authUser = authenticationManager.authenticate(usernamePassword);
        httpServletResponse.addCookie(tokenProvider.generateAuthorizationCookie(users));
        return loginRequest;
    }

    @Override
    public AuthoritiesType getAuthoritiesType() {
        return AuthoritiesType.EMPLOYEE;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return employeeRepository.findEmployeeByEmail(username);
    }
}
