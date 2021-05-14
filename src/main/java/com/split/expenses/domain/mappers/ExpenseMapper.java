package com.split.expenses.domain.mappers;

import com.split.expenses.domain.dtos.ExpenseDto;
import com.split.expenses.domain.entities.Expense;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ValueMapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ExpenseMapper {

    ExpenseMapper INSTANCE = Mappers.getMapper( ExpenseMapper.class );

    Expense expenseDtoToExpense(ExpenseDto expenseDto);

    ExpenseDto expenseToExpenseDto(Expense expense);

    List<ExpenseDto> expenseListToExpenseDto(List<Expense> expense);
}
