package com.split.expenses.domain.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SummaryDto {
    @JsonProperty(value = "total_amount")
    private BigDecimal totalAmount;
    @JsonProperty(value = "expense_quantity")
    private BigInteger expenseQuantity;
    @JsonProperty(value = "lower_expense")
    private BigDecimal lowerExpense;
    @JsonProperty(value = "biggest_expense")
    private BigDecimal biggestExpense;
    @JsonProperty(value = "average_expense")
    private BigDecimal averageExpense;
}
