package com.example.banking_backend.dto;

import com.example.banking_backend.model.AccountStatus;
import com.example.banking_backend.model.AccountType;

public record AccountRequest(
        Long customerId,
        AccountType accountType,
        double initialBalance,
        AccountStatus accountStatus) {
}