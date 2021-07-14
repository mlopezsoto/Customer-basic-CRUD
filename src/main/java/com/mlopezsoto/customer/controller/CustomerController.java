package com.mlopezsoto.customer.controller;

import com.mlopezsoto.customer.api.CustomerApi;
import com.mlopezsoto.customer.model.Customer;
import com.mlopezsoto.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.Optional;

/**
 * Customer Controller. Provides a CRUD interface.
 */
@Controller
public class CustomerController implements CustomerApi {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public ResponseEntity<Customer> addCustomer(Customer customer) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.createCustomer(customer));
    }

    @Override
    public ResponseEntity<Void> deleteCustomer(Long customerId) {
        customerService.deleteCustomer(customerId);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Customer> getCustomerById(Long customerId) {
        Optional<Customer> customer = customerService.getCustomer(customerId);

        if(customer.isPresent()) {
            return ResponseEntity.ok(customer.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Void> updateCustomer(Customer customer) {
        customerService.updateCustomer(customer);
        return ResponseEntity.ok().build();
    }

}
