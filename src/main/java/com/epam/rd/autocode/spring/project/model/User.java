package com.epam.rd.autocode.spring.project.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "NAME")
    private String name;
}
