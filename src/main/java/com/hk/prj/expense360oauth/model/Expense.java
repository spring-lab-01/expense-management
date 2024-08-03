package com.hk.prj.expense360oauth.model;


import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Expense {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private long id;
    private String category;
    @Nonnull
    private String description;
    @Nonnull
    private Double amount;
    private LocalDateTime expenseTime;
    private String email;

    public Expense(long id, String category, String description, Double amount, LocalDateTime expenseTime, String email) {
        this.id = id;
        this.category = category;
        this.description = description;
        this.amount = amount;
        this.expenseTime = expenseTime;
        this.email = email;
    }

    public Expense(){

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getExpenseTime() {
        return expenseTime;
    }

    public void setExpenseTime(LocalDateTime expenseTime) {
        this.expenseTime = expenseTime;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
