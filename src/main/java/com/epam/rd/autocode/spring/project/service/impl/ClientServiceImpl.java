package com.epam.rd.autocode.spring.project.service.impl;

import com.epam.rd.autocode.spring.project.dto.ClientDTO;
import com.epam.rd.autocode.spring.project.exception.AlreadyExistException;
import com.epam.rd.autocode.spring.project.exception.NotFoundException;
import com.epam.rd.autocode.spring.project.model.Client;
import com.epam.rd.autocode.spring.project.repo.ClientRepository;
import com.epam.rd.autocode.spring.project.service.ClientService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final ModelMapper modelMapper;

    public ClientServiceImpl(ClientRepository clientRepository, ModelMapper modelMapper) {
        this.clientRepository = clientRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Page<ClientDTO> getAllClients(Pageable pageable) {
        return clientRepository.findAll(pageable)
                .map(client -> modelMapper.map(client, ClientDTO.class));
    }

    @Override
    public ClientDTO getClientByEmail(String email) {
        return modelMapper.map(clientRepository.findByEmail(email)
                .orElseThrow(()
                        -> new NotFoundException("Not found employee with email " + email)), ClientDTO.class);
    }

    @Override
    public ClientDTO updateClientByEmail(String email, ClientDTO client) {
        Client clientEntity = clientRepository.findByEmail(email)
                .orElseThrow(()
                        -> new NotFoundException("Not found employee with email " + email));

        if (client.getBalance() != null && !client.getBalance().equals(clientEntity.getBalance()))
            clientEntity.setBalance(client.getBalance());
        if (client.getName() != null && !client.getName().equals(clientEntity.getName()))
            clientEntity.setName(client.getName());
        if (client.getEmail() != null && !client.getEmail().equals(clientEntity.getEmail()))
            clientEntity.setEmail(client.getEmail());
        if (client.getPassword() != null)
            clientEntity.setPassword(new BCryptPasswordEncoder().encode(client.getPassword()));

        return modelMapper.map(clientRepository.save(clientEntity), ClientDTO.class);
    }

    @Override
    public void deleteClientByEmail(String email) {
        clientRepository.delete(clientRepository.findByEmail(email)
                .orElseThrow(()
                        -> new NotFoundException("Not found employee with email " + email)));
    }

    @Override
    public ClientDTO addClient(ClientDTO client) {
        clientRepository.findByEmail(client.getEmail()).ifPresent(client1 -> {
            throw new AlreadyExistException("Client with this email already exist");
        });

        Client client1 = new Client();
        client1.setEmail(client.getEmail());
        client1.setName(client.getName());
        client1.setBalance(client.getBalance());
        client1.setPassword(new BCryptPasswordEncoder().encode(client.getPassword()));

        return modelMapper.map(client1, ClientDTO.class);
    }
}
