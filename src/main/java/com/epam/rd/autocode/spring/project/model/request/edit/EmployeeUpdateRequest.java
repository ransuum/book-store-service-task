package com.epam.rd.autocode.spring.project.model.request.edit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeUpdateRequest {
    private String email;
    private String password;
    private String name;
    private LocalDate birthdate;
    private String phone;
}
