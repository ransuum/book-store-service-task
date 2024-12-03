package com.epam.rd.autocode.spring.project.model.request.edit;

import com.epam.rd.autocode.spring.project.model.enums.AuthoritiesType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientUpdateRequest implements UserUpdateRequest {
    private String email;
    private String password;
    private String name;
    private BigDecimal balance;

    @Override
    public AuthoritiesType getAuthoritiesType() {
        return null;
    }
}
