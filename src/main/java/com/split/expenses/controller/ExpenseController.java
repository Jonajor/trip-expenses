package com.split.expenses.controller;

import com.split.expenses.domain.dtos.ExpenseDto;
import com.split.expenses.domain.entities.Expense;
import com.split.expenses.domain.services.ExpenseService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    public ResponseEntity<ExpenseDto> createTrip(@PathVariable String trip, @Valid @RequestBody ExpenseDto expenseDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(expenseService.createExpense(expenseDto));
    }

    @GetMapping("/{trip}")
    public ResponseEntity<List<ExpenseDto>> consultTrip(@PathVariable String trip){
        return ResponseEntity.ok().body(expenseService.expenseList(trip));
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
