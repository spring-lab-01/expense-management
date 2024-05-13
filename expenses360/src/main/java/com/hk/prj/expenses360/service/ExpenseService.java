package com.hk.prj.expenses360.service;

import com.hk.prj.expenses360.model.AppUser;
import com.hk.prj.expenses360.repository.ExpenseRepository;
import com.hk.prj.expenses360.exception.ResourceNotFoundException;
import com.hk.prj.expenses360.model.Expense;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
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
        AppUser appUser = userService.getLoggedInUser();
        if(Objects.nonNull(appUser))
            return expenseRepository.findAllByUserId(appUser.getId());
        else
            throw new RuntimeException("User not found");
    }
    public Expense getExpensesByUser(long id){
        //todo - get expenses by user
        return expenseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found with Id %d".formatted(id)));
    }

    public void save(Expense expense) {
        AppUser appUser = userService.getLoggedInUser();
        if(Objects.nonNull(appUser))
            expense.setUserId(appUser.getId());
        else
            throw new RuntimeException("User not found");
        expenseRepository.save(expense);

    }

    public void delete(long index) {
        Optional<Expense> expenseOptional = expenseRepository.findById(index);
        if(expenseOptional.isPresent()) {
            expenseRepository.delete(expenseOptional.get());
        }
    }
}
