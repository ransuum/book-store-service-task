package com.epam.rd.autocode.spring.project.dto;

import com.epam.rd.autocode.spring.project.model.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookItemDTO {
    private String bookName;
    private Integer quantity;
}
