package com.epam.rd.autocode.spring.project.service.base;

import com.epam.rd.autocode.spring.project.controller.dto.ClientDTO;

import java.util.List;

public interface ClientService {
    List<ClientDTO> getAllClients();

    ClientDTO getClientById(long id);

    ClientDTO updateClientById(long id, ClientDTO client);

    void deleteClientById(long id);

    ClientDTO addClient(ClientDTO client);
}
