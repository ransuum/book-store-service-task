package com.epam.rd.autocode.spring.project.model.request.register;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientReq extends UserReq {
    @NotNull(message = "Balance is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Balance cannot be negative")
    private BigDecimal balance;
}
