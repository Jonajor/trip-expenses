package com.split.expenses.controller;

import com.split.expenses.domain.dtos.ExpenseDto;
import com.split.expenses.domain.entities.Expense;
import com.split.expenses.domain.services.ExpenseService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ExpenseController {

    @Autowired
    ExpenseService expenseService;

    @GetMapping("/login")
    public String login(){
        //TODO: Implementar a parte de login na parte de config
        return "teste trip";
    }

    @PostMapping("/{trip}/expense")
    public Expense createTrip(@PathVariable String trip, @RequestBody @Valid ExpenseDto expenseDto) throws NotFoundException {
        return expenseService.createExpense(expenseDto);
    }

    @GetMapping("/{trip}")
    public String consultTrip(@PathVariable String trip){
        return null;
    }

    @PostMapping("/{trip}/close")
    public Expense closeTrip(@PathVariable String trip){
        return expenseService.closeTrip(trip);
    }

    @GetMapping("/{trip}/summary")
    public String resumeTrip(@PathVariable String trip){
        return null;
    }
}
