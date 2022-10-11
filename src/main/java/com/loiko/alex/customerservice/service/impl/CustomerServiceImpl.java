package com.loiko.alex.customerservice.service.impl;

import com.loiko.alex.customerservice.config.ServiceConfig;
import com.loiko.alex.customerservice.model.Customer;
import com.loiko.alex.customerservice.repository.CustomerRepository;
import com.loiko.alex.customerservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private ServiceConfig config;

    @Autowired
    private MessageSource messages;

    @Override
    public Customer findById(String id) {
        Optional<Customer> maybeCustomer = repository.findById(id);
        if (maybeCustomer.isEmpty()) {
            throw new IllegalArgumentException(String.format(messages.getMessage("customer.search.error.message", null, null), id));
        }
        return maybeCustomer.get();
    }

    @Override
    public Customer save(Customer customer) {
        customer.setCustomerId(UUID.randomUUID().toString());
        return update(customer);
    }

    @Override
    public Customer update(Customer customer) {
        repository.save(customer);
        return customer.withComment(config.getProperty());
    }

    @Override
    public String delete(String id) {
        var customer = new Customer();
        customer.setCustomerId(id);
        repository.delete(customer);
        return String.format(messages.getMessage("customer.delete.message", null, null), id);
    }
}