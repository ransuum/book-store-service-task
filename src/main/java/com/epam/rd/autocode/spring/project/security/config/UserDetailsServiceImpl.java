package com.epam.rd.autocode.spring.project.security.config;

import com.epam.rd.autocode.spring.project.model.Client;
import com.epam.rd.autocode.spring.project.model.Employee;
import com.epam.rd.autocode.spring.project.repo.ClientRepository;
import com.epam.rd.autocode.spring.project.repo.EmployeeRepository;
import com.epam.rd.autocode.spring.project.security.TokenProvider;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final ClientRepository clientRepository;
    private final EmployeeRepository employeeRepository;
    private final TokenProvider tokenProvider;
    private final HttpServletResponse httpServletResponse;

    public UserDetailsServiceImpl(ClientRepository clientRepository, EmployeeRepository employeeRepository,
                                  TokenProvider tokenProvider, HttpServletResponse httpServletResponse) {
        this.clientRepository = clientRepository;
        this.employeeRepository = employeeRepository;
        this.tokenProvider = tokenProvider;
        this.httpServletResponse = httpServletResponse;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return Optional.ofNullable(employeeRepository.findEmployeeByEmail(email))
                .or(() -> Optional.ofNullable(clientRepository.findClientByEmail(email)))
                .map(user -> {
                    if (user instanceof Employee) {
                        httpServletResponse.addCookie(tokenProvider.generateAuthorizationCookie(
                                employeeRepository.findByEmail(email).get()));
                    } else if (user instanceof Client) {
                        httpServletResponse.addCookie(tokenProvider.generateAuthorizationCookie(
                                clientRepository.findByEmail(email).get()));
                    }
                    return user;
                })
                .orElseThrow(() -> new UsernameNotFoundException("No user found with email: " + email));
    }
}
