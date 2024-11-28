package com.epam.rd.autocode.spring.project.controller.withoutSite;

import com.epam.rd.autocode.spring.project.dto.BookDTO;
import com.epam.rd.autocode.spring.project.service.BookService;
import com.epam.rd.autocode.spring.project.util.pagging.PageConfig;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final PageConfig<BookDTO> pageConfig;

    public BookController(BookService bookService, PageConfig<BookDTO> pageConfig) {
        this.bookService = bookService;
        this.pageConfig = pageConfig;
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

    @PutMapping("/{name}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable String name,
                                              @RequestBody BookDTO bookDTO) {
        return ResponseEntity.ok(bookService.updateBookByName(name, bookDTO));
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllBooks(@RequestParam(defaultValue = "0", required = false) Integer page,
                                                           @RequestParam(defaultValue = "10", required = false) Integer size) {
        return ResponseEntity.ok(pageConfig.response(bookService.getAllBooks(PageRequest.of(page, size))));
    }

    @GetMapping("/by-name/{name}")
    public ResponseEntity<BookDTO> getBooksByName(@PathVariable String name) {
        return ResponseEntity.ok(bookService.getBookByName(name));
    }
}

