package com.gusrubin.lab.hexagonal.infrastructure.adapters;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gusrubin.lab.hexagonal.domain.customer.Customer;
import com.gusrubin.lab.hexagonal.domain.customer.CustomerRepositoryPort;
import com.gusrubin.lab.hexagonal.infrastructure.database.entities.CustomerEntity;
import com.gusrubin.lab.hexagonal.infrastructure.database.repositories.CustomerRepository;

/**
 * @author Gustavo Rubin
 */

@Component
public class CustomerRepositoryPortAdapter implements CustomerRepositoryPort {

	private final CustomerRepository repository;
	private final ModelMapper mapper;

	@Autowired
	public CustomerRepositoryPortAdapter(CustomerRepository repository, ModelMapper modelMapper) {
		this.repository = repository;
		this.mapper = modelMapper;
	}

	@Override
	public Customer save(Customer customer) {
		CustomerEntity entity = mapper.map(customer, CustomerEntity.class);
		CustomerEntity persitedEntity = repository.save(entity);

		return mapper.map(persitedEntity, Customer.class);
	}

	@Override
	public List<Customer> findAll() {
		return repository.findAll().stream().map(c -> mapper.map(c, Customer.class)).toList();
	}

	@Override
	public Customer findById(Long id) {
		return mapper.map(repository.findById(id), Customer.class);
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}

}
