package com.epam.rd.autocode.spring.project.controller.base;

import com.epam.rd.autocode.spring.project.controller.dto.OrderDTO;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

// TODO Modify code here

public interface OrderController {

    ModelAndView addOrder(OrderDTO orderDTO, Model model);


    ModelAndView getAllOrders(Model model);
}
