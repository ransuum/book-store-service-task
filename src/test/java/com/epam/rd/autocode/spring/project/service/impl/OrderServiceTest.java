package com.epam.rd.autocode.spring.project.service.impl;

import com.epam.rd.autocode.spring.project.controller.dto.OrderDTO;
import com.epam.rd.autocode.spring.project.model.Order;
import com.epam.rd.autocode.spring.project.service.base.OrderService;
import com.epam.rd.autocode.spring.project.service.repo.OrderRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.epam.rd.autocode.spring.project.tools.ExampleModelCreator.createTestOrder;
import static com.epam.rd.autocode.spring.project.tools.ExampleModelCreator.createTestOrderDTO;
import static com.epam.rd.autocode.spring.project.tools.MethodChecker.isMethodStartsWithAndIsAssignable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private OrderServiceImpl orderService;

    @BeforeAll
    static void testsOverrideMethods() {
        Long numberOfFindOverrideMethods = Arrays.stream(OrderServiceImpl.class.getDeclaredMethods())
                .filter(val -> isMethodStartsWithAndIsAssignable(val, OrderService.class))
                .count();
        assertEquals(4, numberOfFindOverrideMethods,
                "Some methods of OrderService has not implemented right");
    }

    @Test
    @DisplayName("Method getAllOrders launched")
    void getAllOrders_shouldReturnAllOrders() {
        when(orderRepository.findAll()).thenReturn(Collections.singletonList(createTestOrder()));
        when(modelMapper.map(any(Order.class), eq(OrderDTO.class))).thenReturn(createTestOrderDTO());

        List<OrderDTO> result = orderService.getAllOrders();

        assertEquals(1, result.size(),
                "Method getAllOrders() return invalid number of items");
    }

    @Test
    @DisplayName("Method getOrderById launched")
    void getOrderById_shouldReturnOrderById() {
        Long orderId = 1L;
        Order actual = createTestOrder();

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(actual));
        when(modelMapper.map(any(Order.class), eq(OrderDTO.class))).thenReturn(createTestOrderDTO());

        OrderDTO expected = orderService.getOrderById(orderId);

        assertNotNull(expected,
                "Method getOrderById returns null");
        assertEquals(modelMapper.map(actual, OrderDTO.class), expected,
                "Values of actual order and expected are not the same");

        verify(modelMapper, times(2)).map(actual, OrderDTO.class);
    }

    @Test
    @DisplayName("Method deleteOrder launched")
    void deleteOrderById_shouldDeleteOrderById() {
        Long orderIdToDelete = 1L;
        Order orderToDelete = createTestOrder();

        when(orderRepository.findById(orderIdToDelete)).thenReturn(Optional.of(orderToDelete));

        orderService.deleteOrderById(orderIdToDelete);

        verify(orderRepository, times(1)).deleteById(orderIdToDelete);

        assertNull(orderService.getOrderById(orderIdToDelete),
                "Value should be null. After invoking delete method order doesn't become deleted");
    }
}
