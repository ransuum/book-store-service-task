package com.epam.rd.autocode.spring.project.util.edit_manager;

import com.epam.rd.autocode.spring.project.model.enums.AuthoritiesType;
import com.epam.rd.autocode.spring.project.model.request.edit.ClientUpdateRequest;
import com.epam.rd.autocode.spring.project.repo.ClientRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class EditClient implements Edit<ClientUpdateRequest> {
    private final ClientRepository clientRepository;

    public EditClient(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public ClientUpdateRequest edit(ClientUpdateRequest clientUpdateRequest) {
        clientRepository.findByEmail(clientUpdateRequest.getEmail()).ifPresent(client -> {
            if (clientUpdateRequest.getEmail() != null) client.setEmail(clientUpdateRequest.getEmail());
            if (clientUpdateRequest.getPassword() != null)
                client.setPassword(new BCryptPasswordEncoder().encode(clientUpdateRequest.getPassword()));
            if (clientUpdateRequest.getName() != null) client.setName(clientUpdateRequest.getName());
            clientRepository.save(client);
        });

        return clientUpdateRequest;
    }

    @Override
    public AuthoritiesType getAuthoritiesType() {
        return AuthoritiesType.CLIENT;
    }
}
