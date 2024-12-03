package com.epam.rd.autocode.spring.project.model.request.edit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeUpdateRequest extends UserUpdateRequest {
    private LocalDate birthdate;
    private String phone;
}
