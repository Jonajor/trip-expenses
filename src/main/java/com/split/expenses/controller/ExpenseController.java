package com.split.expenses.controller;

import com.split.expenses.domain.dtos.ExpenseDto;
import com.split.expenses.domain.dtos.SummaryDto;
import com.split.expenses.domain.services.ExpenseService;
import com.split.expenses.domain.exceptions.ExpenseNotFoundException;
import com.split.expenses.domain.exceptions.InternalServerErrorException;
import com.split.expenses.domain.exceptions.UnprocessableEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        try{
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(expenseService.createExpense(expenseDto));
        }catch (RuntimeException e){
            throw new UnprocessableEntityException();
        }
    }

    @GetMapping("/{trip}")
    public ResponseEntity<List<ExpenseDto>> consultTrip(@PathVariable String trip,
                                                        @RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "100") int size){
        try{
            Pageable paging = PageRequest.of(page, size);

            var expense = expenseService.expenseList(trip, paging);

            Map<String, Object> response = new HashMap<>();
            response.put("data", expense.get());
            response.put("currentPage", expense.getNumber());
            response.put("totalItems", expense.getTotalElements());
            response.put("totalPages", expense.getTotalPages());
            return new ResponseEntity(response, HttpStatus.PARTIAL_CONTENT);
        } catch (Exception e){
            throw new InternalServerErrorException();
        }
    }

    @PostMapping("/{trip}/close")
    public ResponseEntity<ExpenseDto> closeTrip(@PathVariable String trip){
        if (expenseService.existTrip(trip)){
           return ResponseEntity.status(HttpStatus.CREATED).body(expenseService.closeTrip(trip));
        }
        throw new ExpenseNotFoundException();
    }

    @GetMapping("/{trip}/summary")
    public ResponseEntity<SummaryDto> resumeTrip(@PathVariable String trip){
        if(expenseService.existTrip(trip)){
            return ResponseEntity.ok().body(expenseService.resumeTrip(trip));
        }
        throw  new ExpenseNotFoundException();
    }
}
