
package com.split.expenses.domain.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ExpenseDto {
    private String tripId;
    private BigDecimal amount;
    private String description;
}
