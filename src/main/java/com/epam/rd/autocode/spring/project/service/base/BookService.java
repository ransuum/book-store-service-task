package com.epam.rd.autocode.spring.project.service.base;

import com.epam.rd.autocode.spring.project.controller.dto.BookDTO;

import java.util.List;

public interface BookService {
    List<BookDTO> getAllBooks();

    BookDTO getBookById(Long id);

    BookDTO updateBookById(Long id, BookDTO book);

    void deleteBookById(Long id);

    BookDTO addBook(BookDTO book);
}
