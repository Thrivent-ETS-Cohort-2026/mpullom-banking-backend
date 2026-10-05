package com.example.banking_backend.model;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transferId;

    @NotNull(message = "Sender number failed Spring NotBlank validation")
    @Column(name = "sender")
    private Long senderAccountNumber;

    @NotNull(message = "Receiver number failed Spring NotBlank validation")
    @Column(name = "receiver")
    private Long receiverAccountNumber;

    @NotNull(message = "Amount failed Spring NotBlank validation")
    @Column(name = "amount")
    private Long amount;

    @NotNull(message = "Timestamp failed Spring NotBlank validation")
    @Column(name = "time_stamp")
    private LocalDateTime timestamp;

    public Transfer() {

    }

    public Transfer(@Valid Long senderAccountNumber, @Valid Long receiverAccountNumber, @Valid Long amount, @Valid LocalDateTime timestamp) {
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

    public Long getId() {
        return transferId;
    }

    public Long getSenderAccountNumber() {
        return this.senderAccountNumber;
    }

    public Long getReceiverAccountNumber() {
        return this.receiverAccountNumber;
    }

    public Long getAmount() {
        return this.amount;
    }

    public LocalDateTime getTimestamp() {
        return this.timestamp;
    }

}
