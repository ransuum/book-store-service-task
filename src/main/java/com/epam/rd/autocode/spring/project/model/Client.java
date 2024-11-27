package com.epam.rd.autocode.spring.project.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@Table(name = "CLIENTS")
public class Client extends User {

    @Column(name = "BALANCE", nullable = false)
    private BigDecimal balance;
}
