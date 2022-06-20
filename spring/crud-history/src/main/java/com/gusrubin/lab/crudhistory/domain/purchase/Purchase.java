package com.gusrubin.lab.crudhistory.domain.purchase;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.util.Assert;

import com.gusrubin.lab.crudhistory.domain.product.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Purchase {

	private Long id;
	private Long customerId;
	private List<Product> products;
	private LocalDateTime createdAt;

	public static Purchase create(Long customerId, List<Product> products) {
		Assert.notNull(customerId, "Customer id can't be null");
		Assert.notEmpty(products, "Purchase must have at least one item in product list");

		return Purchase.builder().createdAt(LocalDateTime.now()).customerId(customerId).products(products).build();
	}

}
