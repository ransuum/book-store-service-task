package com.epam.rd.autocode.spring.project.service.impl;

import com.epam.rd.autocode.spring.project.dto.OrderDTO;
import com.epam.rd.autocode.spring.project.exception.NotFoundException;
import com.epam.rd.autocode.spring.project.model.Book;
import com.epam.rd.autocode.spring.project.model.BookItem;
import com.epam.rd.autocode.spring.project.model.Order;
import com.epam.rd.autocode.spring.project.repo.BookRepository;
import com.epam.rd.autocode.spring.project.repo.ClientRepository;
import com.epam.rd.autocode.spring.project.repo.EmployeeRepository;
import com.epam.rd.autocode.spring.project.repo.OrderRepository;
import com.epam.rd.autocode.spring.project.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final ModelMapper modelMapper;
    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final EmployeeRepository employeeRepository;
    private final BookRepository bookRepository;

    public OrderServiceImpl(ModelMapper modelMapper, OrderRepository orderRepository,
                            ClientRepository clientRepository,
                            EmployeeRepository employeeRepository, BookRepository bookRepository) {
        this.modelMapper = modelMapper;
        this.orderRepository = orderRepository;
        this.clientRepository = clientRepository;
        this.employeeRepository = employeeRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public Page<OrderDTO> getOrdersByClient(String clientEmail, Pageable pageable) {
        return orderRepository.findAllByClientEmail(clientEmail, pageable)
                .map(order -> modelMapper.map(order, OrderDTO.class));
    }

    @Override
    public Page<OrderDTO> getOrdersByEmployee(String employeeEmail, Pageable pageable) {
        return orderRepository.findAllByEmployeeEmail(employeeEmail, pageable)
                .map(order -> modelMapper.map(order, OrderDTO.class));
    }

    @Override
    public OrderDTO addOrder(OrderDTO order) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        BigDecimal totalPrice = order.getBookItems().stream()
                .map(item -> {
                    Book book = bookRepository.findByName(item.getBookName())
                            .orElseThrow(() -> new NotFoundException("Book not found: " + item.getBookName()));
                    return book.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        List<BookItem> bookItems = order.getBookItems().stream()
                .map(bookItemDTO -> {
                    Book book = bookRepository.findByName(bookItemDTO.getBookName())
                            .orElseThrow(() -> new NotFoundException("Book not found: " + bookItemDTO.getBookName()));
                    return BookItem.builder()
                            .book(book)
                            .quantity(bookItemDTO.getQuantity())
                            .build();
                })
                .collect(Collectors.toList());

        Order savedOrder = orderRepository.save(Order.builder()
                .orderDate(LocalDateTime.now())
                .price(totalPrice)
                        .employee(employeeRepository.findByEmail(authentication.getName()).orElse(null))
                .client(clientRepository.findByEmail(authentication.getName())
                        .orElse(null))
                .bookItems(bookItems)
                .build());

        savedOrder.getBookItems().forEach(item -> item.setOrder(savedOrder));

        return modelMapper.map(savedOrder, OrderDTO.class);
    }
}
