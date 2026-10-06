package com.example.banking_backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.banking_backend.dto.TransactionRequest;
import com.example.banking_backend.model.Transaction;
import com.example.banking_backend.service.TransactionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    private TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<Transaction> createAccount(@Valid @RequestBody TransactionRequest newTransaction) {
        Transaction savedTransaction = transactionService.createTransaction(newTransaction);
        return new ResponseEntity<>(savedTransaction, HttpStatus.CREATED);

    }
}
