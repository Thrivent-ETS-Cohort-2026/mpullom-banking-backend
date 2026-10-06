package com.example.banking_backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.banking_backend.model.Transfer;
import com.example.banking_backend.model.TransferRequest;
import com.example.banking_backend.service.TransferService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

    @RestController
@RequestMapping("/api/transfers")
public class TransferController {
    
    private TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    // /api/transfers	POSt
    @PostMapping("/")
    public ResponseEntity<Transfer> createAccount(@RequestBody TransferRequest newTransfer) {
        Transfer savedTransfer = transferService.transferFunds(newTransfer);
        return new ResponseEntity<>(savedTransfer, HttpStatus.CREATED);

    }
}
