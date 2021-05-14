
package com.split.expenses.domain.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class ExpenseDto {
    @NotNull(message = "TripId cannot be null")
    @NotBlank(message = "TripId cannot be empty")
    private String tripId;
    @NotNull(message = "Amount cannot be null")
    private BigDecimal amount;
    @NotNull(message = "Description cannot be null")
    private String description;
    @NotNull(message = "Username cannot be null")
    @NotBlank(message = "Username cannot be empty")
    private String username;
}
