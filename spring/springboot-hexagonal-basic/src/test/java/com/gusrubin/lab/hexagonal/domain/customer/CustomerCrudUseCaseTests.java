package com.gusrubin.lab.hexagonal.domain.customer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author Gustavo Rubin
 */

@ExtendWith(MockitoExtension.class)
class CustomerCrudUseCaseTests {

	@Mock
	private CustomerRepositoryPort customerRepository;

	@Spy
	@InjectMocks
	private CustomerService service;

	@Test
	void createCustomerSucessfully() {
		// Preconditions
		Customer customer = CustomerBuilder.buildDefault();
		Mockito.when(customerRepository.save(Mockito.any(Customer.class))).thenReturn(customer);

		// Test
		Customer persistedCustomer = service.create(customer);

		// Validations
		assertEquals(customer.getId(), persistedCustomer.getId());
		assertEquals(customer.getName(), persistedCustomer.getName());
		assertEquals(customer.getEmail(), persistedCustomer.getEmail());
	}

	@Test
	void shouldFailWhenCreateCustomerWithoutName() {
		// Preconditions
		Customer customer = CustomerBuilder.buildDefault();
		customer.setName(null);

		// Test and validations
		assertThrows(IllegalArgumentException.class, () -> service.create(customer));
		Mockito.verify(service, times(1)).create(Mockito.any());
	}

	@Test
	void shouldFailWhenCreateCustomerWithoutEmail() {
		// Preconditions
		Customer customer = CustomerBuilder.buildDefault();
		customer.setEmail(null);

		// Test and validations
		assertThrows(IllegalArgumentException.class, () -> service.create(customer));
		Mockito.verify(service, times(1)).create(Mockito.any());
	}

	/*
	 * TODO Missing implementation of other use case methods tests, such as findAll,
	 * findById, update and delete.
	 * 
	 */

}
