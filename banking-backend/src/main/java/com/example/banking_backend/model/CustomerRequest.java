package com.example.banking_backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
public class CustomerRequest {
    @NotBlank(message = "First name failed Spring NotBlank validation")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "Last name failed Spring NotBlank validation")
    @Column(name = "last_name")
    private String lastName;

    @NotBlank(message = "Email failed Spring NotBlank validation")
    @Email(message = "Invalid email syntax")
    @Column(name = "email")
    private String email;

    @NotBlank(message = "Phone Number failed Spring NotBlank validation")
    @Column(name = "phone_number")
    private String phoneNumber;

    public CustomerRequest() {

    }

    public CustomerRequest(String firstName, String lastName, String email, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String newFirstName) {
        this.firstName = newFirstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String newLastName) {
        this.lastName = newLastName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String newEmail) {
        this.email = newEmail;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String newPhoneNumber) {
        this.phoneNumber = newPhoneNumber;
    }
}