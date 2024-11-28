package com.epam.rd.autocode.spring.project.security;

import com.epam.rd.autocode.spring.project.model.Client;
import com.epam.rd.autocode.spring.project.model.Employee;
import com.epam.rd.autocode.spring.project.repo.ClientRepository;
import com.epam.rd.autocode.spring.project.repo.EmployeeRepository;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//public class UserDetailsServiceImpl implements UserDetailsService {
//    private final ClientRepository clientRepository;
//    private final EmployeeRepository employeeRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        UserDetails userDetails = null;
//        Optional<Client> client = clientRepository.findByEmail(username);
//
//        if (client.isPresent()) {
//            userDetails = new User(
//                    client.get().getEmail(),
//                    client.get().getPassword(),
//                    client.get().getAuthorities()
//            );
//        }
//
//        Optional<Employee> employee = employeeRepository.findByEmail(username);
//
//        if (employee.isPresent()) {
//            userDetails = new User(
//                    employee.get().getEmail(),
//                    employee.get().getPassword(),
//                    employee.get().getAuthorities()
//            );
//        }
//
//        return userDetails;
//    }
//}
