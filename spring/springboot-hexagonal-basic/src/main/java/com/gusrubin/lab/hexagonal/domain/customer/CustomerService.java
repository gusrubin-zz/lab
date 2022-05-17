package com.gusrubin.lab.hexagonal.domain.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

public class CustomerService implements CustomerCrudUseCase {

    private final CustomerRepositoryPort customerRepository;

    @Autowired
    public CustomerService(CustomerRepositoryPort customerRepositoryPort) {
	this.customerRepository = customerRepositoryPort;
    }

    @Override
    public Customer create(Customer customer) {
	// Validations
	Assert.hasText(customer.getName(), "Name is required");
	Assert.hasText(customer.getEmail(), "Email is required");

	// Preparation
	customer.setId(null);

	// Performs method goals
	return customerRepository.save(customer);
    }

    @Override
    public List<Customer> findAll() {
	// Validations

	// Preparation

	// Performs method goals
	return customerRepository.findAll();
    }

    @Override
    public Customer findById(Long id) {
	// Validations
	Assert.notNull(id, "Id is required");

	// Preparation

	// Performs method goals
	return customerRepository.findById(id);
    }

    @Override
    public Customer update(Long id, Customer customer) {
	// Validations
	Assert.notNull(id, "Id is required");
	Assert.hasText(customer.getName(), "Name is required");
	Assert.hasText(customer.getEmail(), "Email is required");
	Customer persistedCustomer = customerRepository.findById(id);
	Assert.notNull(persistedCustomer, "Customer id not registered");

	// Preparation
	persistedCustomer.setName(customer.getName());
	persistedCustomer.setEmail(customer.getEmail());

	// Performs method goals
	return customerRepository.save(persistedCustomer);
    }

    @Override
    public void delete(Long id) {
	// Validations
	Assert.isNull(id, "Id is required");
	Customer persistedCustomer = customerRepository.findById(id);
	Assert.isNull(persistedCustomer, "Customer id not registered");

	// Preparation

	// Performs method goals
	customerRepository.delete(id);
    }

}
