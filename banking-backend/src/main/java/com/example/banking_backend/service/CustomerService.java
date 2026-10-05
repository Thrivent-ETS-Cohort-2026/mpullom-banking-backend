package com.example.banking_backend.service;

import java.util.List;

import com.example.banking_backend.model.Customer;
import com.example.banking_backend.model.CustomerRequest;
import com.example.banking_backend.repository.CustomerRepository;

import jakarta.transaction.Transactional;

public class CustomerService {
    private CustomerRepository customers;

    public CustomerService() {
    };

    @Transactional
    public Customer createCustomer(CustomerRequest newCustomer) {
        Customer account = new Customer(
                newCustomer.getFirstName(),
                newCustomer.getLastName(),
                newCustomer.getEmail(),
                newCustomer.getPhoneNumber());

        customers.save(account);

        return account;
    }

    @Transactional
    public List<Customer> getCustomers() {
        return customers.findAll();
    }

    @Transactional
    public Customer getCustomerById(Long customerId) {
        Customer customer = customers.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer " + customerId + " not found"));
        return customer;
    }
}
