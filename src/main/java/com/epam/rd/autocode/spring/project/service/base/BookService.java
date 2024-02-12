package com.epam.rd.autocode.spring.project.service.base;

import com.epam.rd.autocode.spring.project.controller.dto.BookDTO;

import java.util.List;

public interface BookService {
    List<BookDTO> getAllBooks();

    BookDTO getBookById(long id);

    BookDTO updateBookById(long id, BookDTO book);

    void deleteBookById(long id);

    BookDTO addBook(BookDTO book);
}
