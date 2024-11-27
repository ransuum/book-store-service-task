package com.epam.rd.autocode.spring.project.util.checking_validator;

import com.epam.rd.autocode.spring.project.dto.ClientDTO;
import com.epam.rd.autocode.spring.project.model.Client;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component(value = "Validator")
public class ClientValidator implements Validator<Client, ClientDTO> {

    @Override
    public Client validate(Client o1, ClientDTO o2) {
        if (o2.getBalance() != null && !o2.getBalance().equals(o1.getBalance()))
            o1.setBalance(o1.getBalance());
        if (o2.getName() != null && !o2.getName().equals(o1.getName()))
            o1.setName(o1.getName());
        if (o2.getEmail() != null && !o2.getEmail().equals(o1.getEmail()))
            o1.setEmail(o1.getEmail());
        if (o2.getPassword() != null)
            o1.setPassword(o1.getPassword());

        return o1;
    }
}
