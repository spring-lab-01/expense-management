package com.hk.prj.expense360oauth.repository;

import com.hk.prj.expense360oauth.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findAllByEmailIgnoreCase(String email);
}
