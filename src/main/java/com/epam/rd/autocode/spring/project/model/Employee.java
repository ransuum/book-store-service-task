package com.epam.rd.autocode.spring.project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "employees")
@PrimaryKeyJoinColumn(name = "user_id")
public class Employee extends User {

    @Column(name = "BIRTH_DATE", nullable = false)
    private LocalDate birthDate;

    @Column(name = "PHONE", nullable = false)
    private String phone;
}
