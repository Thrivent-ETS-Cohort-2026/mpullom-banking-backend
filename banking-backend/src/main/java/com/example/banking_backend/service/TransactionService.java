package com.example.banking_backend.service;

import org.springframework.stereotype.Service;

import com.example.banking_backend.model.Transaction;
import com.example.banking_backend.model.TransactionRequest;
import com.example.banking_backend.repository.TransactionRepository;

import jakarta.transaction.Transactional;

@Service 
public class TransactionService {
    private TransactionRepository transactions;

    public TransactionService(TransactionRepository transactions) {
        this.transactions = transactions;
    }

    @Transactional
    public Transaction createTransaction(TransactionRequest newTransaction) {
        Transaction transaction = new Transaction(
                newTransaction.getAccountNumber(),
                newTransaction.getTransactionType(),
                newTransaction.getAmount(),
                newTransaction.getTimestamp());

        transactions.save(transaction);
        

        return transaction;
    }
}