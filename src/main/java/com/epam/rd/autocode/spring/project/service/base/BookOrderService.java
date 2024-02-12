package com.epam.rd.autocode.spring.project.service.base;

import com.epam.rd.autocode.spring.project.controller.dto.BookDTO;
import com.epam.rd.autocode.spring.project.controller.dto.BookItemDTO;
import com.epam.rd.autocode.spring.project.controller.dto.OrderDTO;

import java.util.List;

public interface BookOrderService {
    OrderDTO getActualCart();

    boolean isBooksAdded();

    void addBookItemToOrder(BookDTO dto);

    void calculateOrderPrice(List<BookItemDTO> dtos, Operation operation);
}
