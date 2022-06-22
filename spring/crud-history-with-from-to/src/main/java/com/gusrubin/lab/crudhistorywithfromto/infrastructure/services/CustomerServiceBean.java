package com.gusrubin.lab.crudhistorywithfromto.infrastructure.services;

import org.springframework.stereotype.Service;

import com.gusrubin.lab.crudhistorywithfromto.domain.customer.Customer;
import com.gusrubin.lab.crudhistorywithfromto.domain.customer.CustomerRepositoryPort;
import com.gusrubin.lab.crudhistorywithfromto.domain.customer.CustomerService;
import com.gusrubin.lab.crudhistorywithfromto.domain.history.SaveHistoryAsync;

@Service
public class CustomerServiceBean extends CustomerService {

	public CustomerServiceBean(CustomerRepositoryPort customerRepositoryPort,
			SaveHistoryAsync<Customer> saveHistoryAsync) {
		super(customerRepositoryPort, saveHistoryAsync);
	}

}
