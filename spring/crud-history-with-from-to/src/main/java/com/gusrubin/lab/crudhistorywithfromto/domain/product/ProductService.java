package com.gusrubin.lab.crudhistorywithfromto.domain.product;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.util.Assert;

public class ProductService implements ProductCrudUseCase {

	private final ProductRepositoryPort repository;

	public ProductService(ProductRepositoryPort productRepositoryPort) {
		this.repository = productRepositoryPort;
	}

	@Override
	public Product create(Product product) {
		// Validations
		Assert.notNull(product, "Product can't be null");
		Assert.hasText(product.getName(), "Product name can't be blank");
		Assert.notNull(product.getDescription(), "Product description can't be null");
		Assert.notNull(product.getPrice(), "Product price can't be null");
		Assert.state(product.getPrice().compareTo(BigDecimal.ZERO) > 0, "Product price can't be negative o zero");

		// Prepare
		product.setId(null);

		// Performs method goals
		return repository.save(product);
	}

	@Override
	public List<Product> readAll() {
		// Validations

		// Prepare

		// Performs method goals
		return repository.findAll();
	}

	@Override
	public List<Product> readByIdIn(List<Long> ids) {
		// Validations
		Assert.notEmpty(ids, "Id list must have at least one product id");

		// Prepare

		// Performs method goals
		return repository.findByIdIn(ids);
	}

	@Override
	public Product readById(Long id) {
		// Validations
		Assert.notNull(id, "Product id can't be null");
		Product persistedProduct = repository.findById(id);
		Assert.notNull(persistedProduct, "Product not registered");

		// Prepare

		// Performs method goals
		return persistedProduct;
	}

	@Override
	public Product updateById(Long id, Product product) {
		// Validations
		Assert.notNull(id, "Product id can't be null");
		Assert.notNull(product, "Product can't be null");
		Product persistedProduct = repository.findById(id);
		Assert.notNull(persistedProduct, "Product not registered");
		Assert.hasText(product.getName(), "Product name can't be blank");
		Assert.notNull(product.getDescription(), "Product address can't be null");
		Assert.notNull(product.getPrice(), "Product price can't be null");
		Assert.state(product.getPrice().compareTo(BigDecimal.ZERO) > 0, "Product price can't be negative o zero");

		// Prepare
		product.setId(id);

		// Performs method goals
		return repository.save(product);
	}

	@Override
	public void deleteById(Long id) {
		// Validations
		Assert.notNull(id, "Product id can't be null");
		Product persistedProduct = repository.findById(id);
		Assert.notNull(persistedProduct, "Product not registered");

		// Performs method goals
		repository.deleteById(id);
	}

}
