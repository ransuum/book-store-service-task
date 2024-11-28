package com.epam.rd.autocode.spring.project.service;

import com.epam.rd.autocode.spring.project.dto.BookItemDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BooksItemService {
    Page<BookItemDTO> getAllBookItems(Pageable pageable);

    BookItemDTO getBookItemById(Long id);

    void deleteBookItemById(Long id);

    BookItemDTO addBookItem(BookItemDTO book);
}
