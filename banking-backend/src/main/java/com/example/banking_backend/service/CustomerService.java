package com.example.banking_backend.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.banking_backend.dto.CustomerRequest;
import com.example.banking_backend.model.Account;
import com.example.banking_backend.model.Customer;
import com.example.banking_backend.repository.CustomerRepository;

@Service
public class CustomerService {

    private final CustomerRepository customers;
    private final AccountService accountService;

    public CustomerService(CustomerRepository customers, AccountService accountService) {
        this.customers = customers;
        this.accountService = accountService;
    }

    @Transactional
    public Customer createCustomer(CustomerRequest newCustomer) {
        Customer customer = new Customer(
                newCustomer.firstName(),
                newCustomer.lastName(),
                newCustomer.email(),
                newCustomer.phoneNumber());

        return customers.save(customer);
    }

    @Transactional(readOnly = true)
    public List<Customer> getCustomers() {
        return customers.findAll();
    }

    @Transactional(readOnly = true)
    public Customer getCustomerById(Long customerId) {
        return customers.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer " + customerId + " not found"));
    }

    @Transactional
    public Customer updateCustomer(Long customerId, CustomerRequest customerUpdates) {
        Customer customer = customers.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer " + customerId + " not found"));

        if (customerUpdates.firstName() != null && !customerUpdates.firstName().isBlank()) {
            customer.setFirstName(customerUpdates.firstName());
        }

        if (customerUpdates.lastName() != null && !customerUpdates.lastName().isBlank()) {
            customer.setLastName(customerUpdates.lastName());
        }

        if (customerUpdates.email() != null && !customerUpdates.email().isBlank()) {
            customer.setEmail(customerUpdates.email());
        }

        if (customerUpdates.phoneNumber() != null && !customerUpdates.phoneNumber().isBlank()) {
            customer.setPhoneNumber(customerUpdates.phoneNumber());
        }

        return customers.save(customer);
    }

    @Transactional
    public void deleteCustomer(Long customerId) {
        Customer customer = customers.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer " + customerId + " not found"));

        customers.delete(customer);
    }

    @Transactional(readOnly = true)
    public List<Account> getCustomerAccounts(Long customerId) {
        return accountService.getAccountsByCustomerId(customerId);
    }
}