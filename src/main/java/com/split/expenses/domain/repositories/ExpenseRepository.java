package com.split.expenses.domain.repositories;

import com.split.expenses.domain.entities.Expense;
import com.split.expenses.domain.enums.StatusEnum;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ExpenseRepository extends CrudRepository<Expense, Long> {
    Optional<Expense> findByTripIdAndStatus(String trip, StatusEnum statusEnum);

    List<Expense> findAllByTripIdAndStatus(String trip, StatusEnum statusEnum);

    /*
        SELECT sum(c.AMOUNT) Total, count(c.ID) as QtTotal,
        min(c.AMOUNT) as VALOR, max(c.AMOUNT) as  MAX_VALOR, avg(c.AMOUNT) as Media FROM TRIPS c
        @Query("SELECT u FROM User u WHERE u.status = :status and u.name = :name")
         */

}
