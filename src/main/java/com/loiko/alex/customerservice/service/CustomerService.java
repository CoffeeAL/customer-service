package com.loiko.alex.customerservice.service;

import com.loiko.alex.customerservice.model.Customer;

public interface CustomerService {

    Customer findById(String id);

    Customer save(Customer customer);

    Customer update(Customer customer);

    String delete(String id);
}