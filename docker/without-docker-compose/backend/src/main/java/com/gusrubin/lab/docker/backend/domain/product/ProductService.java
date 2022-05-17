package com.gusrubin.lab.docker.backend.domain.product;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public List<Product> findAll() {
		return productRepository.findAll();
	}

	public Product findById(String productId) {
		Optional<Product> productResult = productRepository.findById(productId);
		if (productResult.isEmpty()) {
			throw new IllegalStateException("Product id not registered");
		}
		return productResult.get();
	}

	public Product create(Product product) {
		if (StringUtils.isEmpty(product.getName())) {
			throw new IllegalArgumentException("Missing product name");
		}
		return productRepository.save(product);
	}

	public void delete(String productId) {
		productRepository.delete(findById(productId));
	}

}
