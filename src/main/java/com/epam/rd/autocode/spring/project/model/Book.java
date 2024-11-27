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
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "GENRE", nullable = false)
    private String genre;

    @Column(name = "AGE_GROUP", nullable = false)
    @Enumerated(EnumType.STRING)
    private AgeGroup ageGroup;

    @Column(name = "PRICE", nullable = false)
    private BigDecimal price;

    @Column(name = "PUBLICATION_DATE", nullable = false)
    private LocalDate publicationDate;

    @Column(name = "AUTHOR", nullable = false)
    private String author;

    @Column(name = "NUMBER_OF_PAGES", nullable = false)
    private Integer pages;

    @Column(name = "CHARACTERISTICS")
    private String characteristics;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "LANGUAGE", nullable = false)
    @Enumerated(EnumType.STRING)
    private Language language;
}
