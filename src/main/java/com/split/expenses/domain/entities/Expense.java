package com.split.expenses.domain.entities;

import com.split.expenses.domain.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="Trips")
public class Expense {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String tripId;
    private BigDecimal amount;
    private String description;
    private StatusEnum status;
    private String username;
}
