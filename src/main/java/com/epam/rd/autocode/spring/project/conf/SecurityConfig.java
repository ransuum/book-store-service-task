package com.epam.rd.autocode.spring.project.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
@Configuration
//@EnableWebSecurity
public class SecurityConfig {}
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeRequests(auth -> {
//                    auth
//                            .requestMatchers("/books/**").permitAll()
//                            .requestMatchers("/employees/**").hasRole("EMPLOYEE")
//                            .requestMatchers("/client/**").hasAnyRole("CLIENT")
//                            .anyRequest().authenticated();
//                })
//                .formLogin(formLogin -> formLogin.loginPage("/login"));
//        return http.build();
//    }
//}
