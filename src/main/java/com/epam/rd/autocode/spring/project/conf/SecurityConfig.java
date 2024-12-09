package com.epam.rd.autocode.spring.project.conf;

import com.epam.rd.autocode.spring.project.repo.ClientRepository;
import com.epam.rd.autocode.spring.project.repo.EmployeeRepository;
import com.epam.rd.autocode.spring.project.security.filter.SecurityFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final ClientRepository clientRepository;
    private final EmployeeRepository employeeRepository;

    @Value("${spring.profiles.active}")
    private String profile;

    public SecurityConfig(ClientRepository clientRepository, EmployeeRepository employeeRepository) {
        this.clientRepository = clientRepository;
        this.employeeRepository = employeeRepository;
    }

    @Autowired
    SecurityFilter securityFilter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return (profile.equals("dev")) ?
                httpSecurity
                        .csrf(AbstractHttpConfigurer::disable)
                        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                        .authorizeHttpRequests(authorize -> authorize
                                .requestMatchers(HttpMethod.POST, "/login", "/register/*", "/logout", "/books/**").permitAll()
                                .requestMatchers(HttpMethod.PUT, "/employees/*").hasRole("EMPLOYEE")
                                .requestMatchers(HttpMethod.GET, "/clients/**", "/employees/**").hasRole("EMPLOYEE")
                                .requestMatchers(HttpMethod.POST, "/employees/**").hasRole("EMPLOYEE")
                                .requestMatchers(HttpMethod.DELETE, "/employees/*").hasRole("EMPLOYEE")
                                .requestMatchers(HttpMethod.POST, "/clients/**").hasRole("EMPLOYEE")
                                .requestMatchers(HttpMethod.PUT, "/clients/**").hasAnyRole("EMPLOYEE", "CLIENTS")
                                .anyRequest().authenticated())
                        .sessionManagement(session -> session
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                        )
                        .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                        .build()
                :
                httpSecurity
                        .csrf(AbstractHttpConfigurer::disable)
                        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                        .authorizeHttpRequests(authorize -> authorize
                                .requestMatchers(HttpMethod.GET, "/home", "/register", "/login").permitAll()
                                .requestMatchers(HttpMethod.POST, "/sign-in", "/thyme/register/*").permitAll()
                                .requestMatchers(HttpMethod.POST, "/logout", "/create", "/edit/acc", "/client/*", "/client/form").hasAnyRole("EMPLOYEE", "CLIENT")
                                .requestMatchers(HttpMethod.POST, "/deleteBook/*", "/create-book", "/employee/*").hasRole("EMPLOYEE")
                                .requestMatchers(HttpMethod.GET, "/show/profile").authenticated()
                                .requestMatchers(HttpMethod.GET, "/create", "/employee/profile","/list/*").hasRole("EMPLOYEE")
                                .requestMatchers(HttpMethod.GET, "/client/*").hasAnyRole("CLIENT", "EMPLOYEE")
                                .anyRequest().authenticated()
                        )
                        .sessionManagement(session -> session
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                        )
                        .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                        .build();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CommandLineRunner addPasswordEncoder() {
        return args -> {
            clientRepository.findAll().forEach(client -> {
                client.setPassword(new BCryptPasswordEncoder().encode(client.getPassword()));
                clientRepository.save(client);
            });

            employeeRepository.findAll().forEach(employee -> {
                employee.setPassword(new BCryptPasswordEncoder().encode(employee.getPassword()));
                employeeRepository.save(employee);
            });
        };
    }
}

