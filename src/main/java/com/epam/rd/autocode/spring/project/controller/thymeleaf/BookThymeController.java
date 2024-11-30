package com.epam.rd.autocode.spring.project.controller.thymeleaf;

import com.epam.rd.autocode.spring.project.dto.BookDTO;
import com.epam.rd.autocode.spring.project.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BookThymeController {

    private final BookService bookService;

    public BookThymeController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/create-book")
    public String createBook(@ModelAttribute BookDTO bookDTO) {
        bookService.addBook(bookDTO);
        return "redirect:/home";
    }
}
