package com.gusrubin.lab.springdocexample.domain.product;

import java.util.List;

public interface ProductUseCase {

	List<Product> findAll();

	Product findById(Long id);

	Product findByName(String name);

	Product create(Product product);

	Product update(Long id, Product product);

	void deleteById(Long id);

}
