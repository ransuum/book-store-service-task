package com.epam.rd.autocode.spring.project.service.impl;

import com.epam.rd.autocode.spring.project.dto.OrderDTO;
import com.epam.rd.autocode.spring.project.exception.NotFoundException;
import com.epam.rd.autocode.spring.project.model.Order;
import com.epam.rd.autocode.spring.project.repo.ClientRepository;
import com.epam.rd.autocode.spring.project.repo.EmployeeRepository;
import com.epam.rd.autocode.spring.project.repo.OrderRepository;
import com.epam.rd.autocode.spring.project.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private final ModelMapper modelMapper;
    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final EmployeeRepository employeeRepository;

    public OrderServiceImpl(ModelMapper modelMapper, OrderRepository orderRepository,
                            ClientRepository clientRepository,
                            EmployeeRepository employeeRepository) {
        this.modelMapper = modelMapper;
        this.orderRepository = orderRepository;
        this.clientRepository = clientRepository;
        this.employeeRepository = employeeRepository;
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
        return modelMapper.map(Order.builder()
                .orderDate(order.getOrderDate())
                .price(order.getPrice())
                .client(clientRepository.findByEmail(order.getClientEmail())
                        .orElseThrow(()
                                -> new NotFoundException("Not found client with email " + order.getClientEmail())))
                .employee(employeeRepository.findByEmail(order.getEmployeeEmail())
                        .orElseThrow(()
                                -> new NotFoundException("Not found employee with email " + order.getEmployeeEmail())))
                .build(), OrderDTO.class);
    }
}
