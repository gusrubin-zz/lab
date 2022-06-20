package com.gusrubin.lab.crudhistory.infrastructure.services;

import org.springframework.stereotype.Service;

import com.gusrubin.lab.crudhistory.domain.customer.CustomerRepositoryPort;
import com.gusrubin.lab.crudhistory.domain.customer.CustomerService;

@Service
public class CustomerServiceBean extends CustomerService {

	public CustomerServiceBean(CustomerRepositoryPort customerRepositoryPort) {
		super(customerRepositoryPort);
	}

}
