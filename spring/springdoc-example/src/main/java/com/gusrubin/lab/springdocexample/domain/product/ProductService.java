package com.gusrubin.lab.springdocexample.domain.product;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProductService implements ProductUseCase {

	private final ProductRepositoryPort productRepository;

	public ProductService(ProductRepositoryPort productRepositoryPort) {
		this.productRepository = productRepositoryPort;
	}

	@Override
	public List<Product> findAll() {
		log.debug("Product findAll()");
		return productRepository.findAll();
	}

	@Override
	public Product findById(Long id) {
		log.debug("Product findById={}", id);
		return productRepository.findById(id);
	}

	@Override
	public Product findByName(String name) {
		log.debug("Product findByName={}", name);
		return productRepository.findByName(name);
	}

	@Override
	public Product create(Product product) {
		product.setId(null);
		log.debug("Product create={}", product);
		return productRepository.save(product);
	}

	@Override
	public Product update(Long id, Product product) {
		log.debug("Product update id={}, product={}", id, product);
		if (findById(id) == null) {
			throw new IllegalStateException("Product id not registered");
		}
		return productRepository.save(product);
	}

	@Override
	public void deleteById(Long id) {
		log.debug("Product deleteById={}", id);
		if (findById(id) == null) {
			throw new IllegalStateException("Product id not registered");
		}
		productRepository.deleteById(id);
	}

}
