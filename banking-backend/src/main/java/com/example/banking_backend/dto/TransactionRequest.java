package com.example.banking_backend.dto;

import java.time.LocalDateTime;
import com.example.banking_backend.model.TransactionType;

public record TransactionRequest(
        Long accountNumber,
        TransactionType transactionType,
        Double amount,
        LocalDateTime timestamp) {
    public TransactionRequest withAccountNumber(Long newAccountNumber) {
        return new TransactionRequest(newAccountNumber, this.transactionType, this.amount, this.timestamp);
    }
}