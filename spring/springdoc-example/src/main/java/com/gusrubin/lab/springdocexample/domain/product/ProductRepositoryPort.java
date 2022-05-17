package com.gusrubin.lab.springdocexample.domain.product;

import java.util.List;

public interface ProductRepositoryPort {
	
	List<Product> findAll();

	Product findById(Long id);

	Product findByName(String name);

	Product save(Product product);

	void deleteById(Long id);

}
