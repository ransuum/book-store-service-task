package com.epam.rd.autocode.spring.project.controller.thymeleaf;

import com.epam.rd.autocode.spring.project.dto.BookDTO;
import com.epam.rd.autocode.spring.project.dto.OrderDTO;
import com.epam.rd.autocode.spring.project.service.BookService;
import com.epam.rd.autocode.spring.project.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/orders")
@Slf4j
public class OrderControllerThyme {
    private final OrderService orderService;
    private final BookService bookService;

    public OrderControllerThyme(OrderService orderService, BookService bookService) {
        this.orderService = orderService;
        this.bookService = bookService;

    }

    @GetMapping("/get-page")
    public String order(Model model,
                        @RequestParam(defaultValue = "0", required = false) Integer page,
                        @RequestParam(defaultValue = "6", required = false) Integer size) {
        model.addAttribute("orderDto", new OrderDTO());
        Page<BookDTO> books = bookService.getAllBooks(PageRequest.of(page, size));
        model.addAttribute("books", books);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", books.getTotalPages());
        return "book-order";
    }

    @PostMapping("/book-order")
    public String orderBook(@ModelAttribute("orderDto") OrderDTO orderDTO) {
        log.info("Bookitems: {}", orderDTO.getBookItems());
        log.info("price: {}", orderService.addOrder(orderDTO).getPrice());
        return "redirect:/home";
    }
}
