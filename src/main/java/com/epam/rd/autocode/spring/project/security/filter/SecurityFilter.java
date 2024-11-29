package com.epam.rd.autocode.spring.project.security.filter;

import com.epam.rd.autocode.spring.project.repo.ClientRepository;
import com.epam.rd.autocode.spring.project.repo.EmployeeRepository;
import com.epam.rd.autocode.spring.project.security.TokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    TokenProvider tokenService;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        var token = this.recoverToken(request);
        if (token != null) {
            var email = tokenService.validateToken(token);
            var user1 = clientRepository.findClientByEmail(email);
            var user2 = employeeRepository.findEmployeeByEmail(email);
            UsernamePasswordAuthenticationToken auth;
            if (user1 == null) auth = new UsernamePasswordAuthenticationToken(user2, null, user2.getAuthorities());
            else auth = new UsernamePasswordAuthenticationToken(user1, null, user1.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            Optional<Cookie> authCookie = Arrays.stream(cookies)
                    .filter(cookie -> cookie.getName().equals("Authorization"))
                    .findFirst();
            if (authCookie.isPresent()) {
                return authCookie.get().getValue();
            }
        }
        return null;
    }
}
