package com.example.banking_backend.model;

// Account DTO
public class AccountRequest {
    private AccountType accountType;

    private Long balance;

    private AccountStatus accountStatus;

    private Long initialDeposit;

    public AccountRequest() {
    }

    public AccountRequest(AccountType accountType, Long initialBalance, AccountStatus accountStatus) {
        this.accountType = accountType;
        this.balance = initialBalance;
        this.accountStatus = accountStatus;
    }

    public AccountType getAccountType() {
        return this.accountType;
    }

    public Long getBalance() {
        return this.balance;
    }

    public AccountStatus getAccountStatus() {
        return this.accountStatus;
    }

    public Long getInitialDeposit() {
        return this.initialDeposit;
    }
}
