package com.gusrubin.lab.crudhistorywithfromto.domain.customer;

import java.util.List;

public interface CustomerCrudUseCase {
	
	Customer create(Customer customer);
	
	List<Customer> readAll();
	
	Customer readById(Long id);
	
	Customer updateById(Long id, Customer customer);
	
	void deleteById(Long id);

}
