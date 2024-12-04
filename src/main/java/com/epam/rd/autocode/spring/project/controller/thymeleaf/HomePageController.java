package com.epam.rd.autocode.spring.project.controller.thymeleaf;

import com.epam.rd.autocode.spring.project.dto.BookDTO;
import com.epam.rd.autocode.spring.project.service.BookService;
import com.epam.rd.autocode.spring.project.service.ClientService;
import com.epam.rd.autocode.spring.project.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
public class HomePageController {
    private final BookService bookService;
    private final ClientService clientService;
    private final EmployeeService employeeService;

    public HomePageController(BookService bookService, ClientService clientService, EmployeeService employeeService) {
        this.bookService = bookService;
        this.clientService = clientService;
        this.employeeService = employeeService;
    }

    @GetMapping("/home")
    public String viewBooksPage(
            @RequestParam(defaultValue = "0", required = false) Integer page,
            @RequestParam(defaultValue = "20", required = false) Integer size,
            Model model,
            @CookieValue(value = "Authorization", required = false) String authCookie
    ) {
        Page<BookDTO> books = bookService.getAllBooks(PageRequest.of(page, size));
        model.addAttribute("books", books);
        model.addAttribute("authenticated", authCookie != null);
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

    @GetMapping("/show/profile")
    public String editPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<String> roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        log.info("roles: {}", roles);
        if (roles.contains("ROLE_EMPLOYEE")) {
            model.addAttribute("employee", employeeService.getEmployeeByEmail(authentication.getName()));
            return "employee-profile";
        } else {
            model.addAttribute("client", clientService.getClientByEmail(authentication.getName()));
            return "client-profile";
        }
    }
}
