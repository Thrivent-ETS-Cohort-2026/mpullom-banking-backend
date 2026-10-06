package com.example.banking_backend.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.banking_backend.dto.AccountRequest;
import com.example.banking_backend.model.Account;
import com.example.banking_backend.model.AccountStatus;
import com.example.banking_backend.repository.AccountRepository;
import com.example.banking_backend.repository.CustomerRepository;

@Service
public class AccountService {

    private final AccountRepository accounts;

    public AccountService(AccountRepository accounts, CustomerRepository customerRepository) {
        this.accounts = accounts;
    }

    @Transactional
    public Account createAccount(AccountRequest newAccount) {
        Account account = new Account(
                newAccount.accountType(),
                newAccount.customerId(),
                newAccount.initialBalance(),
                newAccount.accountStatus());

        accounts.save(account);

        return account;
    }

    @Transactional(readOnly = true)
    public Account getAccountByNum(Long accountNumber) {
        return accounts.findById(accountNumber)
                .orElseThrow(() -> new IllegalArgumentException("Account " + accountNumber + " not found"));
    }

    @Transactional
    public Account updateAccountStatus(Long accountNumber, AccountStatus newStatus) {
        Account account = getAccountByNum(accountNumber);
        account.setAccountStatus(newStatus);
        return accounts.save(account);
    }

    @Transactional
    public void deleteAccount(Long accountNumber) {
        Account account = getAccountByNum(accountNumber);

        if (account.getBalance() != 0) {
            throw new IllegalStateException("Cannot delete account. The balance must be zero.");
        }

        accounts.delete(account);
    }

    @Transactional(readOnly = true)
    public List<Account> getAccountsByCustomerId(Long customerId) {
        return accounts.findByCustomerId(customerId);
    }

    @Transactional
    public Account modifyBalance(Long accountNumber, Long change) {
        Account account = getAccountByNum(accountNumber);
        
        double newBalance = account.getBalance() + change;
        if (newBalance < 0) {
            throw new IllegalArgumentException("Insufficient funds. Operation would result in a negative balance.");
        }

        account.setBalance(newBalance);
        return accounts.save(account);
    }
}