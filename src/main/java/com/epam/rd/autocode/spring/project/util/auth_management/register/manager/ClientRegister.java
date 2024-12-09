package com.epam.rd.autocode.spring.project.util.auth_management.register.manager;

import com.epam.rd.autocode.spring.project.dto.ClientDTO;
import com.epam.rd.autocode.spring.project.exception.AlreadyExistException;
import com.epam.rd.autocode.spring.project.model.Client;
import com.epam.rd.autocode.spring.project.model.enums.AuthoritiesType;
import com.epam.rd.autocode.spring.project.model.request.register.ClientReq;
import com.epam.rd.autocode.spring.project.repo.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClientRegister implements RegisterManager<ClientReq, ClientDTO>{
    private final ClientRepository clientRepository;
    private final ModelMapper modelMapper;

    @Override
    public ClientDTO register(ClientReq user) {
        clientRepository.findByEmail(user.getEmail()).ifPresent(client -> {
            throw new AlreadyExistException("You can't use this email");
        });

        Client client = clientRepository.save(new Client(user.getEmail(),
                new BCryptPasswordEncoder().encode(user.getPassword()),
                user.getName(), user.getBalance()));
        return modelMapper.map(client, ClientDTO.class);
    }

    @Override
    public AuthoritiesType getAuthoritiesType() {
        return AuthoritiesType.CLIENT;
    }
}
