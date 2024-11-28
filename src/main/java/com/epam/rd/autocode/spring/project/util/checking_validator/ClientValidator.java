package com.epam.rd.autocode.spring.project.util.checking_validator;

import com.epam.rd.autocode.spring.project.dto.ClientDTO;
import com.epam.rd.autocode.spring.project.model.Client;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "Validator")
public class ClientValidator implements Validator<Client, ClientDTO> {

    @Override
    public Client validate(Client o1, ClientDTO o2) {
        List<Runnable> validations = List.of(
                () -> {
                    if (!o2.getBalance().equals(o1.getBalance())) o1.setBalance(o2.getBalance());
                },
                () -> {
                    if (!o2.getName().equals(o1.getName())) o1.setName(o2.getName());
                },
                () -> {
                    if (!o2.getEmail().equals(o1.getEmail())) o1.setEmail(o2.getEmail());
                },
                () -> {
                    if (!o2.getPassword().equals(o1.getPassword())) o1.setPassword(o2.getPassword());
                }
        );

        validations.forEach(Runnable::run);
        return o1;
    }
}
