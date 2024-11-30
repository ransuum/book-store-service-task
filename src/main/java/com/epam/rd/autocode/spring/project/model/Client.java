package com.epam.rd.autocode.spring.project.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name = "CLIENTS")
public class Client extends User {

    @Column(name = "BALANCE", nullable = false)
    private BigDecimal balance;

    public Client(String email, String password, String name, BigDecimal balance) {
        super(email, password, name);
        this.balance = balance;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_CLIENT"));
    }
}
