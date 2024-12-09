package com.epam.rd.autocode.spring.project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@Table(name = "EMPLOYEES")
public class Employee extends User {

    @Column(name = "BIRTH_DATE", nullable = false)
    private LocalDate birthDate;

    @Column(name = "PHONE", nullable = false)
    private String phone;

    public Employee(String email, String password, String name, LocalDate birthDate, String phone) {
        super(email, password, name);
        this.birthDate = birthDate;
        this.phone = phone;
    }

    public Employee(Long id, String email, String password, String name, LocalDate birthDate, String phone) {
        super(id, email, password, name);
        this.birthDate = birthDate;
        this.phone = phone;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_EMPLOYEE"));
    }
}
