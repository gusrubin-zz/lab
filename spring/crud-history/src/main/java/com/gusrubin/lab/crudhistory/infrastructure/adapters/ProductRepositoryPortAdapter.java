package com.gusrubin.lab.crudhistory.infrastructure.adapters;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gusrubin.lab.crudhistory.domain.product.Product;
import com.gusrubin.lab.crudhistory.domain.product.ProductRepositoryPort;
import com.gusrubin.lab.crudhistory.infrastructure.database.entities.ProductEntity;
import com.gusrubin.lab.crudhistory.infrastructure.database.repositories.ProductRepository;

@Component
public class ProductRepositoryPortAdapter implements ProductRepositoryPort {

	private final ProductRepository productRepository;
	private final ModelMapper mapper;

	@Autowired
	public ProductRepositoryPortAdapter(ProductRepository productRepository, ModelMapper modelMapper) {
		this.productRepository = productRepository;
		this.mapper = modelMapper;
	}

	@Override
	@Transactional
	public Product save(Product product) {
		ProductEntity productEntity = mapper.map(product, ProductEntity.class);
		ProductEntity persistedProductEntity = productRepository.save(productEntity);
		return mapper.map(persistedProductEntity, Product.class);
	}

	@Override
	@Transactional
	public List<Product> findAll() {
		return productRepository.findAll().stream().map(c -> mapper.map(c, Product.class)).toList();
	}

	@Override
	@Transactional
	public List<Product> findByIdIn(List<Long> ids) {
		List<ProductEntity> list = productRepository.findByIdIn(ids).orElse(new ArrayList<>());
		
		return list.stream().map(c -> mapper.map(c, Product.class)).toList();
	}

	@Override
	@Transactional
	public Product findById(Long id) {
		ProductEntity persistedProductEntity = productRepository.findById(id).orElse(null);
		return persistedProductEntity != null ? mapper.map(persistedProductEntity, Product.class) : null;
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		productRepository.deleteById(id);
	}

}
