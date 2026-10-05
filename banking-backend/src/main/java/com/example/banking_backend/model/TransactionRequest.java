package com.example.banking_backend.model;

import java.time.LocalDateTime;

import jakarta.validation.Valid;

public class TransactionRequest {
    private Long accountNumber;

    private TransactionType transactionType;

    private Long amount;

    private LocalDateTime timestamp;

    public TransactionRequest() {

    }

    public TransactionRequest(Long accountNumber, TransactionType transactionType, @Valid Long amount,
            @Valid LocalDateTime timestamp) {
        if (amount < 0) {
            throw new IllegalArgumentException(
                    "Amount must be a positive integer. Provided: " + amount);
        }

        this.accountNumber = accountNumber;
        this.transactionType = transactionType;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
    }

    public Long getAccountNumber() {
        return this.accountNumber;
    }

    public void setAccountNumber(Long newNumber) {
        this.accountNumber = newNumber;
    }

    public TransactionType getTransactionType() {
        return this.transactionType;
    }

    public Long getAmount() {
        return this.amount;
    }

    public LocalDateTime getTimestamp() {
        return this.timestamp;
    }
}
