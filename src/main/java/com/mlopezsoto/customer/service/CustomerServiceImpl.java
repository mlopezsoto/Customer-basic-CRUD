package com.mlopezsoto.customer.service;

import com.mlopezsoto.customer.data.CustomerEntity;
import com.mlopezsoto.customer.data.CustomerRepository;
import com.mlopezsoto.customer.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.Optional;

/**
 * Implementation of CustomerService that uses database repository.
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        validateCustomer(customer, true);
        CustomerEntity customerEntity = buildCustomerEntity(customer);
        return buildCustomerModel(customerRepository.save(customerEntity));
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        validateCustomer(customer, false);
        CustomerEntity customerEntity = buildCustomerEntity(customer);
        return buildCustomerModel(customerRepository.save(customerEntity));
    }

    @Override
    public void deleteCustomer(Long customerId) {
        customerRepository.deleteById(customerId);
    }

    @Override
    public Optional<Customer> getCustomer(Long customerId) {
        return buildCustomerModelOptional(customerRepository.findById(customerId));
    }

    /**
     *  Builds Customer model object from a {@link CustomerEntity}
     * @param customerEntity
     * @return Customer model object
     */
    private Customer buildCustomerModel(CustomerEntity customerEntity) {
        return new Customer()
                .id(customerEntity.getId())
                .email(customerEntity.getEmail())
                .name(customerEntity.getName());
    }

    /**
     * Builds a Optional<Customer> from a Optional<CustomerEntity>
     * @param customerEntityOptional
     * @return Optional Customer
     */
    private Optional<Customer> buildCustomerModelOptional(Optional<CustomerEntity> customerEntityOptional) {
        if(customerEntityOptional.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(new Customer()
                .id(customerEntityOptional.get().getId())
                .email(customerEntityOptional.get().getEmail())
                .name(customerEntityOptional.get().getName()));
        }
    }

    /**
     * Builds a CustomerEntity from a {@link Customer} instance
     * @param customer
     * @return
     */
    private CustomerEntity buildCustomerEntity(Customer customer) {
        return new CustomerEntity(customer.getId(), customer.getName(), customer.getEmail());

    }

    /**
     * Basic data validation.
     * @throws ValidationException if the data does not conform with the defined requirements.
     * @param customer Customer data
     * @param create true if creating, false if updating.
     */
    private void validateCustomer(Customer customer, boolean create) {
        if(customer == null ||
                (create && customer.getId() != null) ||
                customer.getEmail() == null ||
                customer.getName() == null) {
            throw new ValidationException("Invalid Customer Data");
        }

        if (!create && customer.getId() == null) {
            throw new ValidationException("Invalid ID");
        }
    }
}
