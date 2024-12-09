package com.epam.rd.autocode.spring.project.model.request.register;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeReq {
    @Valid
    @Email(message = "Isn't email")
    @NotBlank(message = "Email is blank")
    @Size(max = 40, message = "Email is too long")
    private String email;

    @Valid
    @NotBlank(message = "Password is blank")
    @Size(min = 9, max = 30, message = "Password size should be from 9 to 30 characters")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&_-])[A-Za-z\\d@$!%*#?&_-]+$",
            message = "Password must contain at least one uppercase letter, one lowercase letter, one number and one special character")
    private String password;

    @NotBlank(message = "Name is blank")
    private String name;

    @NotNull(message = "Birth date is required")
    @Past(message = "Birth date must be in the past")
    private LocalDate birthdate;

    @NotBlank(message = "Phone number cannot be blank")
    @Pattern(
            regexp = "^\\+?[0-9]{10,15}$",
            message = "Phone number must be valid and include 10 to 15 digits"
    )
    private String phone;
}
