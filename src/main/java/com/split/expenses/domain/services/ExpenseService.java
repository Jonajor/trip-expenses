package com.split.expenses.domain.services;

import com.split.expenses.domain.dtos.ExpenseDto;
import com.split.expenses.domain.entities.Expense;
import com.split.expenses.domain.enums.StatusEnum;
import com.split.expenses.domain.repositories.ExpenseRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ExpenseService {

    @Autowired
    ExpenseRepository expenseRepository;

    public Expense createExpense(ExpenseDto expenseDto) throws NotFoundException {

        var teste = expenseRepository.findByTripIdAndStatus(expenseDto.getTripId(), StatusEnum.INACTIVE);
        if (!teste.isPresent() && teste != null) {
            Expense expense = Expense.builder()
                    .amount(expenseDto.getAmount())
                    .tripId(expenseDto.getTripId())
                    .description(expenseDto.getDescription())
                    .status(StatusEnum.ACTIVE)
                    .build();

            return expenseRepository.save(expense);
        } else {
            throw new NotFoundException("expenseDto");
        }

    }

    public Expense closeTrip(String trip) {
        Expense expense = Expense.builder()
                .tripId(trip)
                .status(StatusEnum.INACTIVE)
                .build();
        return expenseRepository.save(expense);
    }
}
