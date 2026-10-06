package com.example.banking_backend.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.banking_backend.dto.TransactionRequest;
import com.example.banking_backend.model.Account;
import com.example.banking_backend.model.Transaction;
import com.example.banking_backend.model.TransactionType;
import com.example.banking_backend.repository.AccountRepository;
import com.example.banking_backend.repository.TransactionRepository;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    @Transactional
    public Transaction createTransaction(TransactionRequest request) {
        Account account = accountRepository.findById(request.accountNumber())
                .orElseThrow(() -> new IllegalArgumentException("Account not found with number: " + request.accountNumber()));

        if (request.amount() == null || request.amount() <= 0) {
            throw new IllegalArgumentException("Transaction amount must be greater than zero.");
        }

        if (request.transactionType() == TransactionType.WITHDRAWL) {
            if (account.getBalance() < request.amount()) {
                throw new IllegalStateException("Insufficient funds for withdrawal.");
            }
            account.setBalance(account.getBalance() - request.amount());
        } else if (request.transactionType() == TransactionType.DEPOSIT) {
            account.setBalance(account.getBalance() + request.amount());
        }

        accountRepository.save(account);

        Transaction transaction = new Transaction(
            account.getAccountNumber(), 
            request.transactionType(),
            request.amount(),
            (request.timestamp() != null) ? request.timestamp() : LocalDateTime.now()
        );

        return transactionRepository.save(transaction);
    }
}