package com.example.banking_backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.banking_backend.model.Account;
import com.example.banking_backend.model.AccountRequest;
import com.example.banking_backend.model.AccountStatus;
import com.example.banking_backend.repository.AccountRepository;

import jakarta.transaction.Transactional;

@Service
public class AccountService {
    private AccountRepository accounts;

    public AccountService() {

    }

    @Transactional
    public Account createAccount(AccountRequest newAccount) {
        Account account = new Account(
                newAccount.getAccountType(),
                newAccount.getBalance(),
                newAccount.getAccountStatus());

        accounts.save(account);

        return account;
    }

    @Transactional
    public Account getAccountByNum(Long accountNumber) {
        Account account = accounts.findById(accountNumber)
                .orElseThrow(() -> new IllegalArgumentException("Account " + accountNumber + " not found"));

        return account;
    }

    @Transactional
    public Account updateAccountStatus(Long accountNumber, AccountStatus newStatus) {
        Account account = accounts.findById(accountNumber)
                .orElseThrow(() -> new IllegalArgumentException("Account " + accountNumber + " not found"));

        account.setAccountStatus(newStatus);
        return account;
    }

    @Transactional
    public void deleteAccount(Long accountNumber) {

        Account account = accounts.findById(accountNumber)
                .orElseThrow(() -> new IllegalArgumentException("Account " + accountNumber + " not found"));

        // if Amount is zero only
        if (account.getBalance() != 0) {
            throw new IllegalStateException("Cannot delete account. The bablance must be zero.");
        }

        accounts.delete(account);
    }

    @Transactional
    public List<Account> getAccountsByCustomerId(Long customerId) {
        List<Account> customerAccounts = accounts.findByCustomerId(customerId);
        return customerAccounts;
    }

    @Transactional
    public Account modifyBalance(Long customerId, Long change) {
        Account account = getAccountByNum(customerId);
        account.setBalance(account.getBalance() + change);
        accounts.save(account);
        return account;
    }
}
