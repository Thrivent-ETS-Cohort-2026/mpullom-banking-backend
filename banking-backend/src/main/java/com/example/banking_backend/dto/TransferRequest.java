package com.example.banking_backend.dto;

import java.time.LocalDateTime;

public record TransferRequest(Long senderAccountNumber, Long receiverAccountNumber, Long amount, LocalDateTime timestamp) {}