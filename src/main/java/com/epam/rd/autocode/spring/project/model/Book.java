package com.epam.rd.autocode.spring.project.model;

import com.epam.rd.autocode.spring.project.model.enums.AgeGroup;
import com.epam.rd.autocode.spring.project.model.enums.Language;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "BOOKS")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "genre", nullable = false)
    private String genre;

    @Column(name = "age_group", nullable = false)
    @Enumerated(EnumType.STRING)
    private AgeGroup ageGroup;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "publication_year", nullable = false)
    private LocalDate publicationDate;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "number_of_pages", nullable = false)
    private Integer pages;

    @Column(name = "characteristics")
    private String characteristics;

    @Column(name = "description")
    private String description;

    @Column(name = "language", nullable = false)
    @Enumerated(EnumType.STRING)
    private Language language;
}
