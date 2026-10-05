package com.example.banking_backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.banking_backend.model.Account;
import com.example.banking_backend.model.AccountRequest;
import com.example.banking_backend.model.AccountStatus;
import com.example.banking_backend.model.Transaction;
import com.example.banking_backend.model.TransactionRequest;
import com.example.banking_backend.service.AccountService;
import com.example.banking_backend.service.TransactionService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;
    private TransactionService transactionService;

    public AccountController(AccountService accountService, TransactionService transactionService) {
        this.accountService = accountService;
        this.transactionService = transactionService;
    }

    @PostMapping("/")
    public ResponseEntity<Account> createAccount(@RequestBody AccountRequest newAccount) {
        Account savedAccount = accountService.createAccount(newAccount);
        return new ResponseEntity<>(savedAccount, HttpStatus.CREATED);
    }

    @GetMapping("/{acctNo}")
    public ResponseEntity<Account> getAccountByNum(@PathVariable Long acctNo) {
        return ResponseEntity.ok(accountService.getAccountByNum(acctNo));
    }

    @PutMapping("/{acctNo}")
    public ResponseEntity<Account> updateAccountStatus(@PathVariable Long acctNo, AccountStatus newStatus) {
        Account savedAccount = accountService.updateAccountStatus(acctNo, newStatus);
        return ResponseEntity.ok(savedAccount);
    }

    @DeleteMapping("/{acctNo}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long acctNo) {
        accountService.deleteAccount(acctNo);
        ;
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{acctNo}/transactions")
    public ResponseEntity<Transaction> recordTransaction(@PathVariable Long acctNo,
            @RequestBody TransactionRequest newTransaction) {
        newTransaction.setAccountNumber(acctNo);
        Transaction savedTransaction = transactionService.createTransaction(newTransaction);
        return new ResponseEntity<>(savedTransaction, HttpStatus.CREATED);
    }
}