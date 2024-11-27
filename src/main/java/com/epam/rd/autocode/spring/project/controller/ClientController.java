package com.epam.rd.autocode.spring.project.controller;

import com.epam.rd.autocode.spring.project.dto.ClientDTO;
import com.epam.rd.autocode.spring.project.dto.EmployeeDTO;
import com.epam.rd.autocode.spring.project.service.ClientService;
import com.epam.rd.autocode.spring.project.util.pagging.PageConfiguration;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/clients")
public class ClientController {
    private final ClientService clientService;
    private final PageConfiguration pageConfiguration;

    public ClientController(ClientService clientService, PageConfiguration pageConfiguration) {
        this.clientService = clientService;
        this.pageConfiguration = pageConfiguration;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getClients(Integer page, Integer size) {
        return ResponseEntity.ok(pageConfiguration.response(clientService.getAllClients(PageRequest.of(page, size))));
    }

    @GetMapping("/by-email/{email}")
    public ResponseEntity<ClientDTO> getClientByEmail(@PathVariable String email) {
        return ResponseEntity.ok(clientService.getClientByEmail(email));
    }

    @PutMapping("/{email}")
    public ResponseEntity<ClientDTO> updateClient(@PathVariable String email,
                                                      @RequestBody ClientDTO clientDTO) {
        return ResponseEntity.ok(clientService.updateClientByEmail(email, clientDTO));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<?> deleteClientByEmail(@PathVariable String email) {
        clientService.deleteClientByEmail(email);
        return ResponseEntity.ok("DELETED");
    }

    @PostMapping
    public ResponseEntity<ClientDTO> addClient(@RequestBody ClientDTO clientDTO) {
        return new ResponseEntity<>(clientService.addClient(clientDTO), HttpStatus.CREATED);
    }
}
