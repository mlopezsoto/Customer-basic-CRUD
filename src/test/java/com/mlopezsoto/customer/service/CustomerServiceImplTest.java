package com.mlopezsoto.customer.service;

import com.mlopezsoto.customer.data.CustomerEntity;
import com.mlopezsoto.customer.data.CustomerRepository;
import com.mlopezsoto.customer.model.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.Mockito.*;

/**
 * {@link CustomerServiceImpl} unit tests. Dependencies are mocked.
 */
class CustomerServiceImplTest {

    @Test
    void deleteCustomer() {
        CustomerRepository customerRepositoryMock = Mockito.mock(CustomerRepository.class);
        CustomerServiceImpl customerService = new CustomerServiceImpl(customerRepositoryMock);

        customerService.deleteCustomer(50L);
        verify(customerRepositoryMock, times(1)).deleteById(50L);
    }

    @Test
    void getCustomerFound() {
        CustomerRepository customerRepositoryMock = Mockito.mock(CustomerRepository.class);
        CustomerServiceImpl customerService = new CustomerServiceImpl(customerRepositoryMock);

        CustomerEntity customerEntity10 = new CustomerEntity(10L, "pepe", "email@company.com");

        when(customerRepositoryMock.findById(10l)).thenReturn(Optional.of(customerEntity10));

        Optional<Customer> customerOptional = customerService.getCustomer(10l);

        verify(customerRepositoryMock, times(1)).findById(10l);
        Assertions.assertTrue(customerOptional.isPresent());
        Customer customer = customerOptional.get();
        Assertions.assertEquals(10L, customer.getId());
        Assertions.assertEquals("pepe", customer.getName());
        Assertions.assertEquals("email@company.com", customer.getEmail());
    }

    @Test
    void getCustomerNotFound() {
        CustomerRepository customerRepositoryMock = Mockito.mock(CustomerRepository.class);
        CustomerServiceImpl customerService = new CustomerServiceImpl(customerRepositoryMock);

        when(customerRepositoryMock.findById(10l)).thenReturn(Optional.empty());
        Optional<Customer> customerOptional = customerService.getCustomer(10l);

        verify(customerRepositoryMock, times(1)).findById(10l);
        Assertions.assertTrue(customerOptional.isEmpty());
    }
}