package com.epam.rd.autocode.spring.project.model.request.register;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientReq {
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

    @NotNull(message = "Balance is required")
    @DecimalMin(value = "0.0", message = "Balance cannot be negative")
    private BigDecimal balance;
}
