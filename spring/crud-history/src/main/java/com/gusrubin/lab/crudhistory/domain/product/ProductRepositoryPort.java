package com.gusrubin.lab.crudhistory.domain.product;

import java.util.List;

public interface ProductRepositoryPort {

	Product save(Product customer);

	List<Product> findAll();
	
	List<Product> findByIdIn(List<Long> ids);

	Product findById(Long id);	

	void deleteById(Long id);

}
