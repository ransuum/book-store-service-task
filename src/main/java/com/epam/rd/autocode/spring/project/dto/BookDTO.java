package com.epam.rd.autocode.spring.project.dto;

import com.epam.rd.autocode.spring.project.model.enums.AgeGroup;
import com.epam.rd.autocode.spring.project.model.enums.Language;
import jakarta.validation.constraints.*;
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
public class BookDTO {
    @NotBlank(message = "Book name cannot be blank")
    @Size(max = 100, message = "Book name must be less than 100 characters")
    private String name;

    @NotBlank(message = "Genre cannot be blank")
    @Size(max = 50, message = "Genre must be less than 50 characters")
    private String genre;

    @NotNull(message = "Age group is required")
    private AgeGroup ageGroup;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero")
    private BigDecimal price;

    @NotNull(message = "Publication date is required")
    @PastOrPresent(message = "Publication date cannot be in the future")
    private LocalDate publicationDate;

    @NotBlank(message = "Author cannot be blank")
    @Size(max = 100, message = "Author name must be less than 100 characters")
    private String author;

    @NotNull(message = "Number of pages is required")
    @Min(value = 4, message = "A book must have at least one page")
    private Integer pages;

    @Size(max = 255, message = "Characteristics must be less than 255 characters")
    private String characteristics;

    @Size(max = 500, message = "Description must be less than 500 characters")
    private String description;

    @NotNull(message = "Language group is required")
    private Language language;
}
