package com.mlopezsoto.customer.data;

import org.springframework.data.repository.CrudRepository;

/**
 * Customer repository.
 */
public interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {
}
