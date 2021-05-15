package com.split.expenses.domain.repositories;

import com.split.expenses.domain.entities.Expense;
import com.split.expenses.domain.enums.StatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ExpenseRepository extends CrudRepository<Expense, Long> {
    Optional<Expense> findByTripIdAndStatus(String trip, StatusEnum statusEnum);

    Page<Expense> findAllByTripIdAndStatus(String trip, StatusEnum statusEnum, Pageable pageable);

    @Query(value = "SELECT sum(e.amount) as Total, count(e.id) as QtdTotal," +
            "min(e.amount) as MIN_VALUE, max(e.amount) as MAX_VALUE, " +
            "avg(e.amount) as media_value FROM TRIPS e", nativeQuery = true)
    Object findAllExpenseTrip();

    Boolean existsByTripId(String tripId);
}
