package com.epam.rd.autocode.spring.project.security.config;

import com.epam.rd.autocode.spring.project.repo.ClientRepository;
import com.epam.rd.autocode.spring.project.repo.EmployeeRepository;
import com.epam.rd.autocode.spring.project.security.TokenProvider;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
        UserDetails employee = employeeRepository.findEmployeeByEmail(email);
        if (employee != null) {
            httpServletResponse.addCookie(tokenProvider.generateAuthorizationCookie(employeeRepository.findByEmail(email).get()));
            return employee;
        }


        UserDetails client = clientRepository.findClientByEmail(email);
        if (client != null) {
            httpServletResponse.addCookie(tokenProvider.generateAuthorizationCookie(clientRepository.findByEmail(email).get()));
            return client;
        }

        throw new UsernameNotFoundException("User not found with email: " + email);
    }
}
