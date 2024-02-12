package com.epam.rd.autocode.spring.project.service.repo;

import com.epam.rd.autocode.spring.project.model.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.epam.rd.autocode.spring.project.tools.ExampleModelCreator.createTestOrder;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OrderRepositoryTest {

    private OrderRepository orderRepository;

    @BeforeEach
    public void setUp() {
        orderRepository = mock(OrderRepository.class);
    }

    @Test
    @DisplayName("Tests OrderRepository Save [1]")
    public void testSaveOrderOnce() {
        Order order = createTestOrder();

        mockSaveBehavior();

        Order saved = orderRepository.save(order);

        assertEquals(1L, saved.getId(),
                "Actual Id should be set correctly after saving the order.");

        verify(orderRepository, times(1)).save(any(Order.class));

    }

    @Test
    @DisplayName("Tests OrderRepository Save [2]")
    public void testSaveOrderTwice() {
        Order order = createTestOrder();

        mockSaveBehavior();

        Order saved = orderRepository.save(order);

        when(orderRepository.save((any(Order.class))))
                .thenThrow(DataIntegrityViolationException.class);

        assertThrows(DataIntegrityViolationException.class, () -> orderRepository.save(order));

        verify(orderRepository, times(2)).save(any(Order.class));

        assertNotNull(saved.getId(),
                "Id cannot be null while saving the order");
    }


    private void mockSaveBehavior() {
        when(orderRepository.save(any(Order.class)))
                .thenAnswer(invocationOnMock -> {
                    Order saved = invocationOnMock.getArgument(0);
                    saved.setId(1L);
                    return saved;
                });
    }

    @Test
    @DisplayName("Tests OrderRepository findById")
    public void testFindById() {
        Long orderId = 1L;
        Order expectedOrder = createTestOrder();
        expectedOrder.setId(orderId);

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(expectedOrder));

        Optional<Order> order = orderRepository.findById(orderId);

        assertTrue(order.isPresent(),
                "Order should exist");

        assertEquals(expectedOrder, order.get(),
                "Actual and expected orders are not the same");

        verify(orderRepository, times(1)).findById(orderId);
    }

    @Test
    @DisplayName("Tests OrderRepository findAll")
    public void testFindAllOrders() {
        List<Order> expected = Arrays.asList(
                createTestOrder(),
                createTestOrder(),
                createTestOrder()
        );

        when(orderRepository.findAll()).thenReturn(expected);

        List<Order> actual = orderRepository.findAll();

        assertEquals(expected, actual,
                "Expected list and actual are not the same");

        verify(orderRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Tests OrderRepository deleteById")
    public void testDeleteOrder() {
        Long orderId = 1L;

        orderRepository.deleteById(orderId);

        verify(orderRepository, times(1)).deleteById(orderId);
    }
}
