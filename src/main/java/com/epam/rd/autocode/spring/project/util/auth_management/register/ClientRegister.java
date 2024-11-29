package com.epam.rd.autocode.spring.project.util.auth_management.register;

import com.epam.rd.autocode.spring.project.exception.AlreadyExistException;
import com.epam.rd.autocode.spring.project.model.Client;
import com.epam.rd.autocode.spring.project.model.enums.AuthoritiesType;
import com.epam.rd.autocode.spring.project.model.request.register.ClientReq;
import com.epam.rd.autocode.spring.project.model.request.register.UserReq;
import com.epam.rd.autocode.spring.project.repo.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClientRegister implements RegisterManager {
    private final ClientRepository clientRepository;
    private final ModelMapper modelMapper;

    @Override
    public <T extends UserReq> T register(T user) {
        ClientReq clientReq = modelMapper.map(user, ClientReq.class);
        clientRepository.findByEmail(clientReq.getEmail()).ifPresent(client -> {
            throw new AlreadyExistException("Client with email " + clientReq.getEmail() + " already exist");
        });
        clientRepository.save(new Client(clientReq.getEmail(),
                new BCryptPasswordEncoder().encode(clientReq.getPassword()),
                clientReq.getName(), clientReq.getBalance()));
        return (T) clientReq;
    }

    @Override
    public AuthoritiesType getAuthoritiesType() {
        return AuthoritiesType.CLIENT;
    }
}
