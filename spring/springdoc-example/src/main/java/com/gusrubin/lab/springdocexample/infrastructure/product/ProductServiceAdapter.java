package com.gusrubin.lab.springdocexample.infrastructure.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gusrubin.lab.springdocexample.domain.product.ProductRepositoryPort;
import com.gusrubin.lab.springdocexample.domain.product.ProductService;

@Service
public class ProductServiceAdapter extends ProductService {

	@Autowired
	public ProductServiceAdapter(ProductRepositoryPort productRepositoryPort) {
		super(productRepositoryPort);
	}

}
