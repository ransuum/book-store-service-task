package com.epam.rd.autocode.spring.project.model.request.edit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserUpdateRequest {
    private String email;
    private String password;
    private String name;
}
