package com.epam.rd.autocode.spring.project.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "clients")
@PrimaryKeyJoinColumn(name = "user_id")
public class Client extends User {

    @Column(name = "BALANCE", nullable = false)
    private BigDecimal balance;
}
