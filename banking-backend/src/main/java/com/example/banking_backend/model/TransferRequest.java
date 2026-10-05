package com.example.banking_backend.model;

import java.time.LocalDateTime;

public class TransferRequest {
    private Long senderAccountNumber;

    private Long receiverAccountNumber;

    private Long amount;

    private LocalDateTime timestamp;

    public TransferRequest() {

    }

    public TransferRequest(Long senderAccountNumber, Long receiverAccountNumber, Long amount, LocalDateTime timestamp) {
        if (senderAccountNumber < 0) {
            throw new IllegalArgumentException(
                    "Sender account number must be a positive integer. Provided: " + senderAccountNumber);
        }

        if (receiverAccountNumber < 0) {
            throw new IllegalArgumentException(
                    "Reciever account number must be a positive integer. Provided: " + senderAccountNumber);
        }

        if (amount < 0) {
            throw new IllegalArgumentException(
                    "Amount must be a positive integer. Provided: " + amount);
        }
        this.senderAccountNumber = senderAccountNumber;
        this.receiverAccountNumber = receiverAccountNumber;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
    }

    public Long getSenderAccountNumber() {
        return this.senderAccountNumber;
    }

    public void setSenderAccountNumber(Long newNumber) {
        this.senderAccountNumber = newNumber;
    }

    public Long getReceiverAccountNumber() {
        return this.receiverAccountNumber;
    }

    public void setReceiverAccountNumber(Long newNumber) {
        this.receiverAccountNumber = newNumber;
    }

    public Long getAmount() {
        return this.amount;
    }

    public LocalDateTime getTimestamp() {
        return this.timestamp;
    }

}
