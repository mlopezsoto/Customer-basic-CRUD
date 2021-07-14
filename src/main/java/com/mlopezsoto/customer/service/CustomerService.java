package com.mlopezsoto.customer.service;

import com.mlopezsoto.customer.model.Customer;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Customer service interface. Provides basic {@link Customer} CRUD functionality. Adds caching to the operations.
 */
@Service
public interface CustomerService {


    /**
     * Creates new Customer.
     * @param customer Customer to be created
     * @return Resulting {@link Customer} after being persisted
     */
    @CachePut(value = "customer", key = "#result.id")
    Customer createCustomer(Customer customer);

    /**
     * Updates a Customer.
     * @param customer Customer to be updated
     * @return Resulting {@link Customer} after being persisted
     */
    @CachePut(value = "customer", key = "#customer.id")
    Customer updateCustomer(Customer customer);

    /**
     * Deletes a {@link Customer}
     * @param customerId Unique customer ID
     */
    @CacheEvict(value = "customer", key = "#customerId")
    void deleteCustomer(Long customerId);

    /**
     * Gets a {@link Customer}
     * @param customerId Unique customer ID
     * @return Optional with {@link Customer} data if found, empty Optional if not.
     */
    @Cacheable(value = "customer", key = "#customerId")
    Optional<Customer> getCustomer(Long customerId);

}
