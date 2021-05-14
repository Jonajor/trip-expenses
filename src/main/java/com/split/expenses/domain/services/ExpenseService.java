package com.split.expenses.domain.services;

import com.split.expenses.domain.dtos.ExpenseDto;
import com.split.expenses.domain.entities.Expense;
import com.split.expenses.domain.enums.StatusEnum;
import com.split.expenses.domain.mappers.ExpenseMapper;
import com.split.expenses.domain.repositories.ExpenseRepository;
import com.split.expenses.exceptions.UnprocessableEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    ExpenseRepository expenseRepository;

    @Autowired
    ExpenseMapper expenseMapper;

    public ExpenseDto createExpense(ExpenseDto expenseDto) {

        var expense = expenseRepository.findByTripIdAndStatus(expenseDto.getTripId(), StatusEnum.INACTIVE);

        if (!expense.isPresent()) {
            var expenseDtoToExpense = expenseMapper.expenseDtoToExpense(expenseDto);
            expenseDtoToExpense.setStatus(StatusEnum.ACTIVE);
            expenseRepository.save(expenseDtoToExpense);
            return expenseMapper.expenseToExpenseDto(expenseDtoToExpense);
        }else{
            throw new UnprocessableEntityException("UnprocessableEntity: Trip already closed");
        }
    }

    public Expense closeTrip(String tripId) {
        Expense expense = Expense.builder()
                .tripId(tripId)
                .status(StatusEnum.INACTIVE)
                .build();
        return expenseRepository.save(expense);
    }

    public List<ExpenseDto> expenseList(String tripId){
        var expense = expenseRepository.findAllByTripIdAndStatus(tripId, StatusEnum.ACTIVE);
        return expenseMapper.expenseListToExpenseDto(expense);
    }

    public Expense resumeTrip(String tripId){

        return null;
    }
}
