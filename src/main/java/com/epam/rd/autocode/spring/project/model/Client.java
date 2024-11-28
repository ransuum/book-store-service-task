package com.epam.rd.autocode.spring.project.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@Data
@Entity
@Builder
@Table(name = "CLIENTS")
public class Client extends User {

    @Column(name = "BALANCE", nullable = false)
    private BigDecimal balance;

    public Client(BigDecimal balance) {
        this.balance = balance;
    }

    public Client(Long id, String email, String password, String name, BigDecimal balance) {
        super(id, email, password, name);
        this.balance = balance;
    }
}
