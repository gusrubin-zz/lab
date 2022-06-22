package com.gusrubin.lab.crudhistorywithfromto.domain.customer;

import java.util.List;

import org.springframework.util.Assert;

import com.gusrubin.lab.crudhistorywithfromto.domain.history.SaveHistoryAsync;

public class CustomerService implements CustomerCrudUseCase {

	private final CustomerRepositoryPort repository;
	private final SaveHistoryAsync<Customer> saveHistoryAsync;

	public CustomerService(CustomerRepositoryPort customerRepositoryPort, SaveHistoryAsync<Customer> saveHistoryAsync) {
		this.repository = customerRepositoryPort;
		this.saveHistoryAsync = saveHistoryAsync;
	}

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
		Customer persistedCustomer = repository.save(customer);

		// Log action in history
		saveHistoryAsync.saveCreationInHistory(true, persistedCustomer);

		return persistedCustomer;
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
		Customer currentCustomer = repository.findById(id);

		// Performs method goals
		Customer updatedCustomer = repository.save(customer);

		// Log action in history
		saveHistoryAsync.saveChangeInHistory(true, currentCustomer, updatedCustomer);

		return updatedCustomer;
	}

	@Override
	public void deleteById(Long id) {
		// Validations
		Assert.notNull(id, "Customer id can't be null");
		Customer persistedCustomer = repository.findById(id);
		Assert.notNull(persistedCustomer, "Customer not registered");

		// Prepare
		Customer currentCustomer = repository.findById(id);

		// Performs method goals
		repository.deleteById(id);

		// Log action in history
		saveHistoryAsync.saveExclusionInHistory(true, currentCustomer);
	}

}
