package com.epam.rd.autocode.spring.project.service.impl;

import com.epam.rd.autocode.spring.project.dto.ClientDTO;
import com.epam.rd.autocode.spring.project.exception.AlreadyExistException;
import com.epam.rd.autocode.spring.project.exception.NotFoundException;
import com.epam.rd.autocode.spring.project.model.Client;
import com.epam.rd.autocode.spring.project.repo.ClientRepository;
import com.epam.rd.autocode.spring.project.service.ClientService;
import com.epam.rd.autocode.spring.project.util.checking_validator.Validator;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final ModelMapper modelMapper;
    private final Validator<Client, ClientDTO> validator;

    public ClientServiceImpl(ClientRepository clientRepository, ModelMapper modelMapper, Validator<Client, ClientDTO> validator) {
        this.clientRepository = clientRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;
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
        return modelMapper.map(clientRepository.save(validator.validate(clientEntity, client)), ClientDTO.class);
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
        client1.setPassword(client.getPassword());

        return modelMapper.map(client1, ClientDTO.class);
    }
}
