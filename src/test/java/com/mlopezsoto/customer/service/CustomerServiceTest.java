package com.mlopezsoto.customer.service;

import com.mlopezsoto.customer.data.CustomerEntity;
import com.mlopezsoto.customer.data.CustomerRepository;
import com.mlopezsoto.customer.model.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
class CustomerServiceTest {

    @MockBean
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    @Test
    void testCacheWellConfigured() {
        CustomerEntity customerEntity10 = new CustomerEntity(10L, "pepe", "email@company.com");

        Customer customer10 = new Customer().email("email@company.com")
                                    .name("pepe lago")
                                    .id(10l);

        CustomerEntity customerEntity10_2 = new CustomerEntity(10L, "pepe lago", "email@company.com");


        when(customerRepository.findById(10l)).thenReturn(Optional.of(customerEntity10));
        customerService.getCustomer(10l);
        verify(customerRepository, times(1)).findById(10l);

        customerService.getCustomer(10l);
        verify(customerRepository, times(1)).findById(10l);

        customerService.deleteCustomer(10L);
        verify(customerRepository, times(1)).deleteById(10l);

        customerService.getCustomer(10l);
        verify(customerRepository, times(2)).findById(10l);


        when(customerRepository.save(any(CustomerEntity.class))).thenReturn(customerEntity10_2);
        customerService.updateCustomer(customer10);
        verify(customerRepository, times(1)).save(any(CustomerEntity.class));
        Optional<Customer> customerFromCache = customerService.getCustomer(10l);
        verify(customerRepository, times(2)).findById(10l); //should come from cache
        Assertions.assertTrue(customerFromCache.isPresent());
        Assertions.assertEquals(customer10, customerFromCache.get());
    }
}