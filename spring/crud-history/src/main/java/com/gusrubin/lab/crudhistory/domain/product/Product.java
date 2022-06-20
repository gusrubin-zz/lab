package com.gusrubin.lab.crudhistory.domain.product;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

	private Long id;
	private String name;
	private String description;
	private BigDecimal price;

}
