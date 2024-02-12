package com.epam.rd.autocode.spring.project.controller.base;

import com.epam.rd.autocode.spring.project.controller.dto.ClientDTO;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

// TODO Modify code here

public interface ClientController {

    ModelAndView getClientById(Long id, Model model);

    ModelAndView showClientAddForm(Model model);

    ModelAndView addClient(ClientDTO clientDTO, BindingResult bindingResult, Model model);

    ModelAndView getAllClients(Model model);

    ModelAndView showClientEditForm(Long id, Model model);

    ModelAndView editClientById(Long id, ClientDTO clientDTO, BindingResult bindingResult, Model model);

    ModelAndView deleteClientById(Long id);
}
