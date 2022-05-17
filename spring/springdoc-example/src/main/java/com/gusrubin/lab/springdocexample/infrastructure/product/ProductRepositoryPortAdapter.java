package com.gusrubin.lab.springdocexample.infrastructure.product;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gusrubin.lab.springdocexample.domain.product.Product;
import com.gusrubin.lab.springdocexample.domain.product.ProductRepositoryPort;

@Component
public class ProductRepositoryPortAdapter implements ProductRepositoryPort {

	private final ProductRepository repository;

	private final ModelMapper mapper;

	@Autowired
	public ProductRepositoryPortAdapter(ProductRepository productRepository, ModelMapper modelMapper) {
		this.repository = productRepository;
		this.mapper = modelMapper;
	}

	@Override
	public List<Product> findAll() {
		return repository.findAll().stream().map(entity -> mapper.map(entity, Product.class)).toList();
	}

	@Override
	public Product findById(Long id) {
		return mapper.map(repository.findById(id), Product.class);
	}

	@Override
	public Product findByName(String name) {
		return mapper.map(repository.findByName(name), Product.class);
	}

	@Override
	public Product save(Product product) {
		return mapper.map(repository.save(mapper.map(product, ProductEntity.class)), Product.class);
	}

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
	}

}
