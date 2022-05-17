package com.gusrubin.lab.docker.backend.web.product;

import java.util.UUID;

import com.gusrubin.lab.docker.backend.domain.product.Product;

public class ProductDto {

	private UUID id;
	private String name;
	private String description;
	
	public ProductDto() {}

	public ProductDto(Product product) {
		super();
		this.id = product.getId();
		this.name = product.getName();
		this.description = product.getDescription();
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Product toEntity() {
		return new Product(this.getName(), this.getDescription());
	}
	
	public ProductDto build() {
		return this;
	}

}
