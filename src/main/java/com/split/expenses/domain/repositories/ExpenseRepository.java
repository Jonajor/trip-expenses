package com.split.expenses.domain.repositories;

import com.split.expenses.domain.enums.StatusEnum;
import org.springframework.data.repository.CrudRepository;
import com.split.expenses.domain.entities.Expense;

import java.util.Optional;

public interface ExpenseRepository extends CrudRepository<Expense, Long> {
    Optional<Expense> findByTripIdAndStatus(String trip, StatusEnum statusEnum);
}
