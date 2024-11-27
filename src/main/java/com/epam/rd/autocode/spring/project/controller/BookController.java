package com.epam.rd.autocode.spring.project.controller;

import com.epam.rd.autocode.spring.project.dto.BookDTO;
import com.epam.rd.autocode.spring.project.service.BookService;
import com.epam.rd.autocode.spring.project.util.pagging.PageConfiguration;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final PageConfiguration pageConfiguration;

    public BookController(BookService bookService, PageConfiguration pageConfiguration) {
        this.bookService = bookService;
        this.pageConfiguration = pageConfiguration;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllBooks(@RequestParam Integer page,
                                                           @RequestParam Integer size) {
        return ResponseEntity.ok(pageConfiguration.response(bookService.getAllBooks(PageRequest.of(page, size))));
    }

    @GetMapping("/by-name/{name}")
    public ResponseEntity<BookDTO> getBooksByName(@PathVariable String name) {
        return ResponseEntity.ok(bookService.getBookByName(name));
    }

    @PutMapping("/{name}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable String name,
                                              @RequestBody BookDTO bookDTO) {
        return ResponseEntity.ok(bookService.updateBookByName(name, bookDTO));
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<?> deleteBook(@PathVariable String name) {
        bookService.deleteBookByName(name);
        return ResponseEntity.ok("DELETED");
    }

    @PostMapping
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTO) {
        return new ResponseEntity<>(bookService.addBook(bookDTO), HttpStatus.CREATED);
    }
}

