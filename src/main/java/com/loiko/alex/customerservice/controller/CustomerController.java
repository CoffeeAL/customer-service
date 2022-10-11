package com.loiko.alex.customerservice.controller;

import com.loiko.alex.customerservice.model.Customer;
import com.loiko.alex.customerservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("v1/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> findCustomer(@PathVariable("customerId") String customerId) {
        Customer customer = customerService.findById(customerId);
        customer.add(
                linkTo(methodOn(CustomerController.class).findCustomer(customer.getCustomerId())).withSelfRel(),
                linkTo(methodOn(CustomerController.class).saveCustomer(customer)).withRel("saveCustomer"),
                linkTo(methodOn(CustomerController.class).updateCustomer(customer)).withRel("updateCustomer"),
                linkTo(methodOn(CustomerController.class).deleteCustomer(customer.getCustomerId())).withRel("deleteCustomer"));
        return ResponseEntity.ok(customer);
    }

    @PostMapping
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer request) {
        return ResponseEntity.ok(customerService.save(request));
    }

    @PutMapping
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer request) {
        return ResponseEntity.ok(customerService.update(request));
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("customerId") String customerId) {
        return ResponseEntity.ok(customerService.delete(customerId));
    }
}