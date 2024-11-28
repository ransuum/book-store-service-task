package com.epam.rd.autocode.spring.project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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

    public Employee(Long id, String email, String password, String name, LocalDate birthDate) {
        super(id, email, password, name);
        this.birthDate = birthDate;
    }

    public Employee(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
