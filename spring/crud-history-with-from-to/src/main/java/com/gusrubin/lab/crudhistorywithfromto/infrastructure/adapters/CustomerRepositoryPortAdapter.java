package com.gusrubin.lab.crudhistorywithfromto.infrastructure.adapters;

import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.gusrubin.lab.crudhistorywithfromto.domain.customer.Customer;
import com.gusrubin.lab.crudhistorywithfromto.domain.customer.CustomerRepositoryPort;
import com.gusrubin.lab.crudhistorywithfromto.infrastructure.database.entities.AddressEntity;
import com.gusrubin.lab.crudhistorywithfromto.infrastructure.database.entities.CustomerEntity;
import com.gusrubin.lab.crudhistorywithfromto.infrastructure.database.repositories.AddressRepository;
import com.gusrubin.lab.crudhistorywithfromto.infrastructure.database.repositories.CustomerRepository;

@Component
public class CustomerRepositoryPortAdapter implements CustomerRepositoryPort {

	private final CustomerRepository customerRepository;
	private final AddressRepository addressRepository;
	private final ModelMapper mapper;

	public CustomerRepositoryPortAdapter(CustomerRepository customerRepository, AddressRepository addressRepository,
			ModelMapper modelMapper) {
		this.customerRepository = customerRepository;
		this.addressRepository = addressRepository;
		this.mapper = modelMapper;
	}

	@Override
	@Transactional
	public Customer save(Customer customer) {
		CustomerEntity customerEntity = mapper.map(customer, CustomerEntity.class);

		// If is updating entity
		if (customer.getId() != null) {
			CustomerEntity persistedCustomerEntity = customerRepository.findById(customer.getId()).orElse(null);
			AddressEntity addressEntity = mapper.map(customer.getAddress(), AddressEntity.class);
			customerEntity.setAddress(addressEntity);
			customerEntity.getAddress().setId(persistedCustomerEntity.getAddress().getId());
		}

		CustomerEntity persistedCustomerEntity = customerRepository.save(customerEntity);
		return mapper.map(persistedCustomerEntity, Customer.class);
	}

	@Override
	@Transactional
	public List<Customer> findAll() {
		return customerRepository.findAll().stream().map(c -> mapper.map(c, Customer.class)).toList();
	}

	@Override
	@Transactional
	public Customer findById(Long id) {
		CustomerEntity persistedCustomerEntity = customerRepository.findById(id).orElse(null);
		return persistedCustomerEntity != null ? mapper.map(persistedCustomerEntity, Customer.class) : null;
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		customerRepository.deleteById(id);
	}

}
