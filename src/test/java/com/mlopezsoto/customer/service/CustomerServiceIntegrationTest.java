package com.mlopezsoto.customer.service;

import com.mlopezsoto.customer.model.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class CustomerServiceIntegrationTest {

    @Autowired
    private CustomerService customerService;

    @Test
    void testCreateNewCustomer() {
        Customer customer = new Customer().email("email@company.com")
                .name("pepe lago");

        Customer createdCustomer = customerService.createCustomer(customer);
        Assertions.assertNotNull(createdCustomer);
        Assertions.assertNotNull(createdCustomer.getId());

    }

    @Test
    void testGetCustomer() {
        Customer customer = new Customer().email("boris@frank.einstein.com")
                .name("boris karloff");

        Customer createdCustomer = customerService.createCustomer(customer);
        Optional<Customer> lookedUpCustomer = customerService.getCustomer(createdCustomer.getId());

        Assertions.assertTrue(lookedUpCustomer.isPresent());
        Assertions.assertEquals(createdCustomer, lookedUpCustomer.get());
    }
}
