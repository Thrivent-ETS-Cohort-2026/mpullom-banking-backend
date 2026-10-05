package com.example.banking_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.banking_backend.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
    
}
