package com.example.banking_backend.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long transactionId;

    @NotNull(message = "Account number is required")
    @Column(name = "account_number", nullable = false)
    private Long accountNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type")
    private TransactionType transactionType;

    @Min(value = 0, message = "Amount must be greater than or equal to 0")
    @Column(name = "amount", nullable = false)
    private double amount;

    @NotNull(message = "Timestamp is required")
    @Column(name = "time_stamp", nullable = false)
    private LocalDateTime timestamp;

    public Transaction() {
    }

    public Transaction(Long accountNumber, TransactionType transactionType, double amount, LocalDateTime timestamp) {
        if (accountNumber != null && accountNumber < 0) {
            throw new IllegalArgumentException("Account Number must be positive. Provided: " + accountNumber);
        }

        if (amount < 0) {
            throw new IllegalArgumentException("Amount must be positive. Provided: " + amount);
        }

        this.accountNumber = accountNumber;
        this.transactionType = transactionType;
        this.amount = amount;
        this.timestamp = (timestamp != null) ? timestamp : LocalDateTime.now();
    }

    public Long getId() {
        return this.transactionId;
    }

    public Long getAccountNumber() {
        return this.accountNumber;
    }

    public TransactionType getTransactionType() {
        return this.transactionType;
    }

    public double getAmount() {
        return this.amount;
    }

    public LocalDateTime getTimestamp() {
        return this.timestamp;
    }
}