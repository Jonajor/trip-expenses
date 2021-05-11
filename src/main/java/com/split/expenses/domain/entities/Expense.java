package com.split.expenses.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.split.expenses.domain.enums.StatusEnum;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Builder
@Data
@Entity
@Table(name="Trips")
public class Expense {
    @Id
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String tripId;
    private BigDecimal amount;
    private String description;
    private StatusEnum status;
}
