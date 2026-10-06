package com.example.banking_backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountNumber;

    @Column(name = "customer_id")
    private Long customerId;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Column(name = "balance")
    private double balance;

    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;

    public Account() {
    }

    public Account(AccountType accountType, Long customerId, double initialBalance, AccountStatus accountStatus) {
        this.accountType = accountType;
        this.balance = initialBalance;
        this.accountStatus = accountStatus;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public AccountType getAccountType() {
        return this.accountType;
    }

    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double newBalance) {
        this.balance = newBalance;
    }

    public AccountStatus getAccountStatus() {
        return this.accountStatus;
    }

    public void setAccountStatus(AccountStatus newStatus) {
        this.accountStatus = newStatus;
    }
}
