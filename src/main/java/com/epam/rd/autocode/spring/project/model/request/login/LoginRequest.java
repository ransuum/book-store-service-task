package com.epam.rd.autocode.spring.project.model.request.login;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class LoginRequest {
    @Valid
    @Email(message = "Isn't email")
    @NotBlank(message = "Email is blank")
    @Size(max = 40, message = "Email is too long")
    private String email;

    @Valid
    @NotBlank(message = "Password is blank")
    @Size(min = 4, max = 30, message = "Password size should be from 9 to 30 characters")
    private String password;
}
