package com.epam.rd.autocode.spring.project.dto;

import com.epam.rd.autocode.spring.project.model.Book;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookItemDTO {
    @NotBlank(message = "Book name cannot be blank")
    @Size(max = 100, message = "Book name must be less than 100 characters")
    private String bookName;

    @NotBlank(message = "quantity not be blank")
    private Integer quantity;
}
