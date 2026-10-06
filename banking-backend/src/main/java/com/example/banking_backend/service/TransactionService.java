package com.example.banking_backend.service;

import org.springframework.stereotype.Service;

import com.example.banking_backend.model.Transaction;
import com.example.banking_backend.model.TransactionRequest;
import com.example.banking_backend.model.TransactionType;
import com.example.banking_backend.repository.TransactionRepository;

import jakarta.transaction.Transactional;

@Service 
public class TransactionService {
    private TransactionRepository transactions;
    private AccountService accountService;

    public TransactionService(TransactionRepository transactions, AccountService accountService) {
        this.transactions = transactions;
        this.accountService = accountService;
    }

    @Transactional
    public Transaction createTransaction(TransactionRequest newTransaction) {
        Transaction transaction = new Transaction(
                newTransaction.getAccountNumber(),
                newTransaction.getTransactionType(),
                newTransaction.getAmount(),
                newTransaction.getTimestamp());

        transactions.save(transaction);
        
        if (newTransaction.getTransactionType() == TransactionType.DEPOSIT){
            accountService.modifyBalance(newTransaction.getAccountNumber(), newTransaction.getAmount());
        }
        else if (newTransaction.getTransactionType() == TransactionType.WITHDRAWL){
            accountService.modifyBalance(newTransaction.getAccountNumber(), -newTransaction.getAmount());
        }
        ;
        
        return transaction;
    }
}