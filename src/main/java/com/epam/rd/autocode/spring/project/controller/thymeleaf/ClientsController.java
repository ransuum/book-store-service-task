package com.epam.rd.autocode.spring.project.controller.thymeleaf;

import com.epam.rd.autocode.spring.project.dto.ClientDTO;
import com.epam.rd.autocode.spring.project.service.ClientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/list")
public class ClientsController {
    private final ClientService clientService;

    public ClientsController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/clients")
    public String getPage(Model model,
                          @RequestParam(defaultValue = "0", required = false) Integer page,
                          @RequestParam(defaultValue = "20", required = false) Integer size) {
        Page<ClientDTO> clientDTOS = clientService.getAllClients(PageRequest.of(page, size));
        model.addAttribute("client", clientDTOS);
        return "clientsInfo";
    }

    @PostMapping("/delete-client/{email}")
    public String deleteClient(@PathVariable String email) {
        clientService.deleteClientByEmail(email);
        return "redirect:/list/clients";
    }
}
