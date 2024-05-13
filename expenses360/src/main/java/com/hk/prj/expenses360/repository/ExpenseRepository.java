package com.hk.prj.expenses360.repository;

import com.hk.prj.expenses360.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findAllByUserId(Long id);
}
