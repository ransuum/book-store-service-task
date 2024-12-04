package com.epam.rd.autocode.spring.project.controller.thymeleaf;

import com.epam.rd.autocode.spring.project.model.request.edit.ClientUpdateRequest;
import com.epam.rd.autocode.spring.project.service.ClientService;
import com.epam.rd.autocode.spring.project.util.edit_manager.config.EditConfig;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/client")
public class ClientEditController {
    private final EditConfig editConfig;

    public ClientEditController(EditConfig editConfig) {
        this.editConfig = editConfig;
    }

    @GetMapping("/form")
    public String editClient(Model model) {
        model.addAttribute("clientUpdate", new ClientUpdateRequest());
        return "client-edit";
    }

    @PostMapping("/edit")
    public String client(@ModelAttribute("clientUpdate") ClientUpdateRequest clientUpdateRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        editConfig.clientUpdate().edit(authentication.getName(), clientUpdateRequest);
        return "redirect:/client-profile";
    }
}
