package com.epam.rd.autocode.spring.project.model.request.edit;

import com.epam.rd.autocode.spring.project.model.enums.AuthoritiesType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeUpdateRequest implements UserUpdateRequest {
    private String email;
    private String password;
    private String name;
    private LocalDate birthdate;
    private String phone;

    @Override
    public AuthoritiesType getAuthoritiesType() {
        return AuthoritiesType.EMPLOYEE;
    }
}
