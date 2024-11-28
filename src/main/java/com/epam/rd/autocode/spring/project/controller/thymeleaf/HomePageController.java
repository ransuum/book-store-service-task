package com.epam.rd.autocode.spring.project.controller.thymeleaf;

import com.epam.rd.autocode.spring.project.dto.BookDTO;
import com.epam.rd.autocode.spring.project.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomePageController {
    private final BookService bookService;

    public HomePageController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/home")
    public String viewBooksPage(
            @RequestParam(defaultValue = "0", required = false) Integer page,
            @RequestParam(defaultValue = "10", required = false) Integer size,
            Model model
    ) {
        Page<BookDTO> books = bookService.getAllBooks(PageRequest.of(page, size));
        model.addAttribute("books", books);
        return "home-page";
    }

    @PostMapping("/deleteBook/{name}")
    public String deleteBook(@PathVariable String name) {
        bookService.deleteBookByName(name);
        return "redirect:/home";
    }

    @GetMapping("/create")
    public String showCreateBookForm(Model model) {
        model.addAttribute("newBook", new BookDTO());
        return "create";
    }

    @PostMapping("/create-book")
    public String createBook(@RequestBody BookDTO bookDTO) {
        bookService.addBook(bookDTO);
        return "redirect:/home";
    }
}