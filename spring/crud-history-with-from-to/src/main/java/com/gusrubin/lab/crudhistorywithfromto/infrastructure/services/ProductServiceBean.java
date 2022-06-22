package com.gusrubin.lab.crudhistorywithfromto.infrastructure.services;

import org.springframework.stereotype.Service;

import com.gusrubin.lab.crudhistorywithfromto.domain.product.ProductRepositoryPort;
import com.gusrubin.lab.crudhistorywithfromto.domain.product.ProductService;

@Service
public class ProductServiceBean extends ProductService {

	public ProductServiceBean(ProductRepositoryPort productRepositoryPort) {
		super(productRepositoryPort);
	}

}
