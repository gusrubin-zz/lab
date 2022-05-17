package com.gusrubin.lab.springdocexample.domain.product;

import java.util.List;

public class ProductBuilder {

	public static Product buildDefault() {
		return Product.builder().id(1L).name("Default").build();
	}

	public static List<Product> buildList() {
		return List.of(Product.builder().id(1L).name("Default").build(),
				Product.builder().id(2L).name("Default2").build());
	}

}
