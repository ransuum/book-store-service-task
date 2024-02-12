package com.epam.rd.autocode.spring.project.service.base;

import com.epam.rd.autocode.spring.project.controller.dto.OrderDTO;

import java.util.*;

public interface OrderService {
    List<OrderDTO> getAllOrders();

    OrderDTO getOrderById(long id);

    void deleteOrderById(long id);

    OrderDTO addOrder(OrderDTO order);
}
