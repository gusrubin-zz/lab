package com.gusrubin.lab.hexagonal.infrastructure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gusrubin.lab.hexagonal.domain.customer.CustomerRepositoryPort;
import com.gusrubin.lab.hexagonal.domain.customer.CustomerService;

/**
 * @author Gustavo Rubin
 */

@Service
public class CustomerServiceImpl extends CustomerService {

	@Autowired
	public CustomerServiceImpl(CustomerRepositoryPort customerRepositoryPort) {
		super(customerRepositoryPort);
	}

}
