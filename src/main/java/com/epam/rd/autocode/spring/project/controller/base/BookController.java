package com.epam.rd.autocode.spring.project.controller.base;

import com.epam.rd.autocode.spring.project.controller.dto.BookDTO;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

// TODO Modify code here

public interface BookController {

    ModelAndView showBookAddForm(Model model);

    ModelAndView getBookById(Long id, Model model);

    ModelAndView addBook( BookDTO bookDTO, BindingResult bindingResult, Model model);

    ModelAndView showBookEditForm(Long id, Model model);

    ModelAndView updateBookById(Long id, BookDTO bookDTO, BindingResult bindingResult, Model model);

    ModelAndView deleteBookById(Long id);

    ModelAndView getAllBooks(Model model);
}
