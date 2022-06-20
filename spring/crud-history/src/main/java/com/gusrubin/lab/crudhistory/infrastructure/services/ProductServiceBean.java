package com.gusrubin.lab.crudhistory.infrastructure.services;

import org.springframework.stereotype.Service;

import com.gusrubin.lab.crudhistory.domain.product.ProductRepositoryPort;
import com.gusrubin.lab.crudhistory.domain.product.ProductService;

@Service
public class ProductServiceBean extends ProductService {

	public ProductServiceBean(ProductRepositoryPort productRepositoryPort) {
		super(productRepositoryPort);
	}

}
