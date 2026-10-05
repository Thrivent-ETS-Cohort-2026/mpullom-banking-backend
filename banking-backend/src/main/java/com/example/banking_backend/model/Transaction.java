package com.example.banking_backend.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transationId;

    @NotNull(message = "Account number failed Spring NotBlank validation")
    @Column(name = "account_number")
    private Long accountNumber;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @NotNull(message = "Amount failed Spring NotBlank validation")
    @Column(name = "amount")
    private Long amount;

    @NotNull(message = "Timestamp failed Spring NotBlank validation")
    @Column(name = "time_stamp")
    private LocalDateTime timestamp;

    public Transaction() {

    }

    public Transaction(@Valid Long accountNumber, TransactionType transactionType, @Valid Long amount,
            @Valid LocalDateTime timestamp) {
        if (accountNumber < 0) {
            throw new IllegalArgumentException(
                    "Account Number must be a positive integer. Provided: " + accountNumber);
        }

        if (amount < 0) {
            throw new IllegalArgumentException(
                    "Amount must be a positive integer. Provided: " + amount);
        }

        this.accountNumber = accountNumber;
        this.transactionType = transactionType;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
    }

    public Long getId() {
        return transationId;
    }

    public Long getAccountNumber() {
        return this.accountNumber;
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
