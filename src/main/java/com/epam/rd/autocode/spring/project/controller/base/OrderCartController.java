package com.epam.rd.autocode.spring.project.controller.base;

import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

// TODO Modify code here

public interface OrderCartController {

    ModelAndView showActiveOrderCart(Model model);

    ModelAndView addBookToOrderCart(Long id, Model model);
}
