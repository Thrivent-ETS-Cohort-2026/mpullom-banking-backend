package com.example.banking_backend.service;

import org.springframework.stereotype.Service;

import com.example.banking_backend.model.Transfer;
import com.example.banking_backend.model.TransferRequest;
import com.example.banking_backend.repository.TransferRepository;

import jakarta.transaction.Transactional;

@Service
public class TransferService {
    private TransferRepository transfers;
    private AccountService accountService;

    public TransferService(TransferRepository transfers, AccountService accountService) {
        this.transfers = transfers;
        this.accountService = accountService;
    }

    @Transactional 
    public Transfer transferFunds(TransferRequest newTransfer) {
        Transfer transfer = new Transfer(
                newTransfer.getSenderAccountNumber(),
                newTransfer.getReceiverAccountNumber(),
                newTransfer.getAmount(),
                newTransfer.getTimestamp());
        transfers.save(transfer);

        //Modify account balances accordingly
        accountService.modifyBalance(newTransfer.getSenderAccountNumber(), -newTransfer.getAmount());
        accountService.modifyBalance(newTransfer.getReceiverAccountNumber(), newTransfer.getAmount());

        return transfer;
    }
}
