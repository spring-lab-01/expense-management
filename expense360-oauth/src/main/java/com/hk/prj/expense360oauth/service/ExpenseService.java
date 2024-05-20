package com.hk.prj.expense360oauth.service;

import com.hk.prj.expense360oauth.exception.ResourceNotFoundException;
import com.hk.prj.expense360oauth.model.Expense;
import com.hk.prj.expense360oauth.repository.ExpenseRepository;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final UserService userService;

    public ExpenseService(ExpenseRepository expenseRepository, UserService userService){
        this.expenseRepository = expenseRepository;
        this.userService = userService;
    }

    public List<Expense> getExpensesByUser(){
        return expenseRepository.findAllByEmailIgnoreCase(userService.getLoggedInUserEmail());
    }
    public Expense getExpensesByUser(long id){
        //todo - get expenses by user
        return expenseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found with Id %d".formatted(id)));
    }

    public void save(Expense expense) {
        expense.setEmail(userService.getLoggedInUserEmail());
        expenseRepository.save(expense);
    }

    public void delete(long index) {
        Optional<Expense> expenseOptional = expenseRepository.findById(index);
        if(expenseOptional.isPresent()) {
            expenseRepository.delete(expenseOptional.get());
        }
    }
}
