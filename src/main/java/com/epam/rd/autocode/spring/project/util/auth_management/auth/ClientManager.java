package com.epam.rd.autocode.spring.project.util.auth_management.auth;

import com.epam.rd.autocode.spring.project.exception.NotFoundException;
import com.epam.rd.autocode.spring.project.model.Client;
import com.epam.rd.autocode.spring.project.model.enums.AuthoritiesType;
import com.epam.rd.autocode.spring.project.model.request.login.LoginRequest;
import com.epam.rd.autocode.spring.project.repo.ClientRepository;
import com.epam.rd.autocode.spring.project.security.TokenProvider;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class ClientManager implements AuthManager, UserDetailsService {

    private final ClientRepository clientRepository;

    public ClientManager(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public LoginRequest login(LoginRequest loginRequest, AuthenticationManager authenticationManager, TokenProvider tokenProvider, HttpServletResponse httpServletResponse) {
        Client users = clientRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(()-> new NotFoundException("user with this email not found"));

        var usernamePassword = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
        var authUser = authenticationManager.authenticate(usernamePassword);
        httpServletResponse.addCookie(tokenProvider.generateAuthorizationCookie(users));
        return loginRequest;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return clientRepository.findClientByEmail(username);
    }

    @Override
    public AuthoritiesType getAuthoritiesType() {
        return AuthoritiesType.CLIENT;
    }
}
