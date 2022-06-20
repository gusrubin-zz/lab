package com.gusrubin.lab.crudhistory.domain.customer;

import java.util.List;

import org.springframework.util.Assert;

import com.gusrubin.lab.crudhistory.domain.history.ActionType;
import com.gusrubin.lab.crudhistory.domain.history.LogInHistory;

public class CustomerService implements CustomerCrudUseCase {

	private final CustomerRepositoryPort repository;

	public CustomerService(CustomerRepositoryPort customerRepositoryPort) {
		this.repository = customerRepositoryPort;
	}

	@LogInHistory(context = "customer", action = ActionType.CREATE)
	@Override
	public Customer create(Customer customer) {
		// Validations
		Assert.notNull(customer, "Customer can't be null");
		Assert.hasText(customer.getName(), "Customer name can't be blank");
		Assert.notNull(customer.getAddress(), "Customer address can't be null");
		Assert.hasText(customer.getAddress().getStreet(), "Customer address street can't be blank");
		Assert.hasText(customer.getAddress().getCity(), "Customer address city can't be blank");
		Assert.hasText(customer.getAddress().getCountry(), "Customer address country can't be blank");

		// Prepare
		customer.setId(null);

		// Performs method goals
		return repository.save(customer);
	}

	@Override
	public List<Customer> readAll() {
		// Validations

		// Prepare

		// Performs method goals
		return repository.findAll();
	}

	@Override
	public Customer readById(Long id) {
		// Validations
		Assert.notNull(id, "Customer id can't be null");
		Customer persistedCustomer = repository.findById(id);
		Assert.notNull(persistedCustomer, "Customer not registered");

		// Prepare

		// Performs method goals
		return persistedCustomer;
	}

	@LogInHistory(context = "customer", action = ActionType.UPDATE)
	@Override
	public Customer updateById(Long id, Customer customer) {
		// Validations
		Assert.notNull(id, "Customer id can't be null");
		Assert.notNull(customer, "Customer can't be null");
		Customer persistedCustomer = repository.findById(id);
		Assert.notNull(persistedCustomer, "Customer not registered");
		Assert.hasText(customer.getName(), "Customer name can't be blank");
		Assert.notNull(customer.getAddress(), "Customer address can't be null");
		Assert.hasText(customer.getAddress().getStreet(), "Customer address street can't be blank");
		Assert.hasText(customer.getAddress().getCity(), "Customer address city can't be blank");
		Assert.hasText(customer.getAddress().getCountry(), "Customer address country can't be blank");

		// Prepare
		customer.setId(id);

		// Performs method goals
		return repository.save(customer);
	}

	@LogInHistory(context = "customer", action = ActionType.DELETE)
	@Override
	public void deleteById(Long id) {
		// Validations
		Assert.notNull(id, "Customer id can't be null");
		Customer persistedCustomer = repository.findById(id);
		Assert.notNull(persistedCustomer, "Customer not registered");

		// Performs method goals
		repository.deleteById(id);
	}

}
