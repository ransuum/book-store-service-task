package com.epam.rd.autocode.spring.project.model.request.register;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeReq extends UserReq {
    @NotNull(message = "Birth date is required")
    @Past(message = "Birth date must be in the past")
    private LocalDate birthDate;

    @NotBlank(message = "Phone number cannot be blank")
    @Pattern(
            regexp = "^\\+?[0-9]{10,15}$",
            message = "Phone number must be valid and include 10 to 15 digits"
    )
    private String phone;
}
