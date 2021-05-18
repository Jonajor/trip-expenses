package com.split.expenses.domain.services;

import com.split.expenses.domain.dtos.ExpenseDto;
import com.split.expenses.domain.dtos.SummaryDto;
import com.split.expenses.domain.entities.Expense;
import com.split.expenses.domain.enums.StatusEnum;
import com.split.expenses.domain.mappers.MapperObject;
import com.split.expenses.domain.repositories.ExpenseRepository;
import com.split.expenses.domain.exceptions.UnprocessableEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    ExpenseRepository expenseRepository;

    @Autowired
    MapperObject mapperObject;

    public Boolean existTrip(String tripId){
        return expenseRepository.existsByTripId(tripId);
    }

    public ExpenseDto createExpense(ExpenseDto expenseDto) {

        var expense = expenseRepository.findByTripIdAndStatus(expenseDto.getTripId(), StatusEnum.INACTIVE);

        if (!expense.isPresent()) {
            var expenseDtoToExpense = mapperObject.expenseDtoToExpense(expenseDto);
            expenseDtoToExpense.setStatus(StatusEnum.ACTIVE);
            expenseRepository.save(expenseDtoToExpense);
            return mapperObject.expenseToExpenseDto(expenseDtoToExpense);
        }else{
            throw new UnprocessableEntityException();
        }
    }

    public ExpenseDto closeTrip(String tripId) {
        Expense expense = Expense.builder()
                .tripId(tripId)
                .status(StatusEnum.INACTIVE)
                .build();
        return mapperObject.expenseToExpenseDto(expenseRepository.save(expense));
    }

    public Page<ExpenseDto> expenseList(String tripId, Pageable paging){
        var expense = expenseRepository.findAllByTripIdAndStatus(tripId, StatusEnum.ACTIVE, paging);
        var expenseDto =  mapperObject.expenseListToExpenseDto(expense);
        Page<ExpenseDto> pages = new PageImpl<ExpenseDto>(expenseDto, expense.getPageable(), expense.getTotalElements());
        return pages;
    }

    public SummaryDto resumeTrip(String tripId) {
        var expense = expenseRepository.findAllExpenseTrip();
        var listExpense = List.of(expense);
        var summary = new SummaryDto();

        Iterator itr = listExpense.iterator();
        while (itr.hasNext()) {
            Object[] obj = (Object[]) itr.next();
            summary.setTotalAmount((BigDecimal) obj[0]);
            summary.setExpenseQuantity((BigInteger) obj[1]);
            summary.setLowerExpense((BigDecimal) obj[2]);
            summary.setBiggestExpense((BigDecimal) obj[3]);
            verifyMedia(summary, obj);
        }
        return summary;
    }

    private void verifyMedia(SummaryDto summary, Object[] obj) {
        if (summary.getExpenseQuantity().compareTo(BigInteger.ONE) > 0) {
            summary.setAverageExpense((BigDecimal) obj[4]);
        } else {
            summary.setAverageExpense((BigDecimal) obj[0]);
        }
    }
}
