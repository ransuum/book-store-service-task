package com.epam.rd.autocode.spring.project.util.profile_chooser;

import com.epam.rd.autocode.spring.project.dto.ClientDTO;
import com.epam.rd.autocode.spring.project.dto.OrderDTO;
import com.epam.rd.autocode.spring.project.service.ClientService;
import com.epam.rd.autocode.spring.project.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.List;

@Component
public class ClientProfile implements Profile {
    private final ClientService clientService;
    private final OrderService orderService;

    public ClientProfile(ClientService clientService, OrderService orderService) {
        this.clientService = clientService;
        this.orderService = orderService;
    }

    @Override
    public String showProfile(String email, List<String> roles, Model model) {
        ClientDTO clientDTO = clientService.getClientByEmail(email);
            model.addAttribute("client", clientDTO);
            Page<OrderDTO> ordersByClient = orderService.getOrdersByClient(
                    clientDTO.getEmail(),
                    PageRequest.of(page, size)
            );
            model.addAttribute("ordersClient", ordersByClient);
            return "client-profile";
    }

    @Override
    public Boolean getName() {
        return Boolean.FALSE;
    }
}
