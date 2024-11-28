package com.epam.rd.autocode.spring.project.controller.withoutSite;

import com.epam.rd.autocode.spring.project.dto.OrderDTO;
import com.epam.rd.autocode.spring.project.service.OrderService;
import com.epam.rd.autocode.spring.project.util.pagging.PageConfig;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final PageConfig<OrderDTO> pageConfig;

    public OrderController(OrderService orderService, PageConfig<OrderDTO> pageConfig) {
        this.orderService = orderService;
        this.pageConfig = pageConfig;
    }

    @GetMapping("/by-client/{clientEmail}")
    public ResponseEntity<Map<String, Object>> getOrdersByClient(@PathVariable String clientEmail,
                                                                 @RequestParam(defaultValue = "0", required = false) Integer page,
                                                                 @RequestParam(defaultValue = "10", required = false) Integer size) {
        return ResponseEntity.ok(pageConfig.response(orderService.getOrdersByClient(clientEmail, PageRequest.of(page, size))));
    }

    @GetMapping("/by-employee/{employeeEmail}")
    public ResponseEntity<Map<String, Object>> getOrdersByEmployee(@PathVariable String employeeEmail,
                                                                 @RequestParam(defaultValue = "0", required = false) Integer page,
                                                                 @RequestParam(defaultValue = "10", required = false) Integer size) {
        return ResponseEntity.ok(pageConfig.response(orderService.getOrdersByEmployee(employeeEmail, PageRequest.of(page, size))));
    }

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody @Valid OrderDTO orderDTO) {
        return new ResponseEntity<>(orderService.addOrder(orderDTO), HttpStatus.CREATED);
    }

}
