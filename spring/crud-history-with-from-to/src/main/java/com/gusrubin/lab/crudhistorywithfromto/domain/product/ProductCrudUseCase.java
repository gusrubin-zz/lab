package com.gusrubin.lab.crudhistorywithfromto.domain.product;

import java.util.List;

public interface ProductCrudUseCase {

	Product create(Product customer);
	
	List<Product> readAll();

	List<Product> readByIdIn(List<Long> ids);	

	Product readById(Long id);

	Product updateById(Long id, Product customer);

	void deleteById(Long id);

}
