package com.example.banking_backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.banking_backend.model.Account;
import com.example.banking_backend.model.Customer;
import com.example.banking_backend.model.CustomerRequest;
import com.example.banking_backend.repository.CustomerRepository;

import jakarta.transaction.Transactional;

@Service 
public class CustomerService {
    private CustomerRepository customers;
    private AccountService accountService;

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

    // Validate Email
    @Transactional
    public Customer updateCustomer(Long customerId, CustomerRequest customerUpdates) {
        Customer customer = customers.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer " + customerId + " not found"));
        if (customerUpdates.getFirstName() != null && !customerUpdates.getFirstName().isBlank()) {
            customer.setFirstName(customerUpdates.getFirstName());
        }

        if (customerUpdates.getLastName() != null && !customerUpdates.getLastName().isBlank()) {
            customer.setLastName(customerUpdates.getLastName());
        }

        if (customerUpdates.getEmail() != null && !customerUpdates.getEmail().isBlank()) {
            customer.setEmail(customerUpdates.getEmail());
        }

        if (customerUpdates.getPhoneNumber() != null && !customerUpdates.getPhoneNumber().isBlank()) {
            customer.setPhoneNumber(customerUpdates.getPhoneNumber());
        }

        return customer;
    }

    // Validate customer has no money in accounts before deleting
    @Transactional
    public void deleteCustomer(Long customerId) {
        Customer customer = customers.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer " + customerId + " not found"));

        customers.delete(customer);
    }

    @Transactional
    public List<Account> getCustomerAccounts(Long customerId) {
        List<Account>  customerAccounts = accountService.getAccountsByCustomerId(customerId);
        return customerAccounts;
    }
}
