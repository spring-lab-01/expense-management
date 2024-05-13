package com.hk.prj.expense360oauth.service;

import com.hk.prj.expense360oauth.exception.ResourceNotFoundException;
import com.hk.prj.expense360oauth.model.Expense;
import com.hk.prj.expense360oauth.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository){
        this.expenseRepository = expenseRepository;
    }

    public List<Expense> getExpensesByUser(){
        //todo - get expenses by user
        return expenseRepository.findAll();
    }
    public Expense getExpensesByUser(long id){
        //todo - get expenses by user
        return expenseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found with Id %d".formatted(id)));
    }

    public void save(Expense expense) {
        expenseRepository.save(expense);
    }

    public void delete(long index) {
        Optional<Expense> expenseOptional = expenseRepository.findById(index);
        if(expenseOptional.isPresent()) {
            expenseRepository.delete(expenseOptional.get());
        }
    }
}
