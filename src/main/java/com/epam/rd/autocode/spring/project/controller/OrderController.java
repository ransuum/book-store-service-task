package com.epam.rd.autocode.spring.project.controller;

import com.epam.rd.autocode.spring.project.dto.OrderDTO;
import com.epam.rd.autocode.spring.project.service.OrderService;
import com.epam.rd.autocode.spring.project.util.pagging.PageConfiguration;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final PageConfiguration pageConfiguration;

    public OrderController(OrderService orderService, PageConfiguration pageConfiguration) {
        this.orderService = orderService;
        this.pageConfiguration = pageConfiguration;
    }

    @GetMapping("/by-client/{clientEmail}")
    public ResponseEntity<Map<String, Object>> getOrdersByClient(@PathVariable String clientEmail,
                                                                 @RequestParam(defaultValue = "0") Integer page,
                                                                 @RequestParam(defaultValue = "10") Integer size) {
        return ResponseEntity.ok(pageConfiguration.response(orderService.getOrdersByClient(clientEmail, PageRequest.of(page, size))));
    }

    @GetMapping("/by-employee/{employeeEmail}")
    public ResponseEntity<Map<String, Object>> getOrdersByEmployee(@PathVariable String employeeEmail,
                                                                 @RequestParam(defaultValue = "0") Integer page,
                                                                 @RequestParam(defaultValue = "10") Integer size) {
        return ResponseEntity.ok(pageConfiguration.response(orderService.getOrdersByEmployee(employeeEmail, PageRequest.of(page, size))));
    }

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        return new ResponseEntity<>(orderService.addOrder(orderDTO), HttpStatus.CREATED);
    }

}
