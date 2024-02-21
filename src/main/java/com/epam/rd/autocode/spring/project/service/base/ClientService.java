package com.epam.rd.autocode.spring.project.service.base;

import com.epam.rd.autocode.spring.project.controller.dto.ClientDTO;

import java.util.List;

public interface ClientService {
    List<ClientDTO> getAllClients();

    ClientDTO getClientById(Long id);

    ClientDTO updateClientById(Long id, ClientDTO client);

    void deleteClientById(Long id);

    ClientDTO addClient(ClientDTO client);
}
