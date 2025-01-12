package com.epam.rd.autocode.spring.project.controller.thymeleaf;

import com.epam.rd.autocode.spring.project.dto.BookDTO;
import com.epam.rd.autocode.spring.project.dto.ClientDTO;
import com.epam.rd.autocode.spring.project.dto.EmployeeDTO;
import com.epam.rd.autocode.spring.project.dto.OrderDTO;
import com.epam.rd.autocode.spring.project.service.BookService;
import com.epam.rd.autocode.spring.project.service.ClientService;
import com.epam.rd.autocode.spring.project.service.EmployeeService;
import com.epam.rd.autocode.spring.project.service.OrderService;
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
    private final OrderService orderService;

    public HomePageController(BookService bookService, ClientService clientService, EmployeeService employeeService, OrderService orderService) {
        this.bookService = bookService;
        this.clientService = clientService;
        this.employeeService = employeeService;
        this.orderService = orderService;
    }

    @GetMapping("/home")
    public String viewBooksPage(
            @RequestParam(defaultValue = "0", required = false) Integer page,
            @RequestParam(defaultValue = "6", required = false) Integer size,
            Model model,
            @CookieValue(value = "Authorization", required = false) String authCookie
    ) {
        Page<BookDTO> books = bookService.getAllBooks(PageRequest.of(page, size));
        model.addAttribute("books", books);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", books.getTotalPages());
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
    public String editPage(Model model,
                           @RequestParam(defaultValue = "0", required = false) Integer page,
                           @RequestParam(defaultValue = "50", required = false) Integer size) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<String> roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        if (roles.contains("ROLE_EMPLOYEE")) {
            EmployeeDTO employeeDTO = employeeService.getEmployeeByEmail(authentication.getName());
            model.addAttribute("employee", employeeDTO);
            Page<OrderDTO> ordersByEmployee = orderService.getOrdersByEmployee(
                    employeeDTO.getEmail(),
                    PageRequest.of(page, size)
            );
            model.addAttribute("ordersEmployee", ordersByEmployee);
            return "employee-profile";
        } else {
            ClientDTO clientDTO = clientService.getClientByEmail(authentication.getName());
            model.addAttribute("client", clientDTO);
            Page<OrderDTO> ordersByClient = orderService.getOrdersByClient(
                    clientDTO.getEmail(),
                    PageRequest.of(page, size)
            );
            model.addAttribute("ordersClient", ordersByClient);
            return "client-profile";
        }
    }
}
