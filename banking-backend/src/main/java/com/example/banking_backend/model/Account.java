package com.example.banking_backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
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

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @NotNull(message = "balance deposit failed Spring NotBlank validation")
    @Column(name = "balance")
    private Long balance;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;

    public Account() {
    }

    public Account(AccountType accountType, @Valid Long initialBalance, AccountStatus accountStatus) {
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

    public Long getBalance() {
        return this.balance;
    }

    public void setBalance(Long newBalance) {
        this.balance = newBalance;
    }

    public AccountStatus getAccountStatus() {
        return this.accountStatus;
    }

    public void setAccountStatus(AccountStatus newStatus) {
        this.accountStatus = newStatus;
    }
}
