package com.epam.rd.autocode.spring.project.controller.thymeleaf;

import com.epam.rd.autocode.spring.project.service.BookService;
import org.springframework.stereotype.Controller;

@Controller
public class BookThymeController {

    private final BookService bookService;

    public BookThymeController(BookService bookService) {
        this.bookService = bookService;
    }


}
