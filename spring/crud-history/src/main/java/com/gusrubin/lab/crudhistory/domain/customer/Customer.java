package com.gusrubin.lab.crudhistory.domain.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

	private Long id;
	private String name;
	private Address address;

	@Data
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	static class Address {

		private String street;
		private String city;
		private String country;

	}

}
