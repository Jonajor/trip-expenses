package com.split.expenses.domain.mappers;

import com.split.expenses.domain.dtos.ExpenseDto;
import com.split.expenses.domain.dtos.UserDto;
import com.split.expenses.domain.entities.Expense;
import com.split.expenses.domain.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper
public interface MapperObject {

    MapperObject INSTANCE = Mappers.getMapper( MapperObject.class );

    Expense expenseDtoToExpense(ExpenseDto expenseDto);

    ExpenseDto expenseToExpenseDto(Expense expense);

    List<ExpenseDto> expenseListToExpenseDto(Page<Expense> expense);

    UserEntity userDtoTOUser(UserDto userDto);

    UserDto userToUserDto(UserEntity userEntity);
}
