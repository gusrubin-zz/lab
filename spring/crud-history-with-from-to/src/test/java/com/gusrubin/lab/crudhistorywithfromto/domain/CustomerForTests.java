package com.gusrubin.lab.crudhistorywithfromto.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerForTests {

	private Long id;
	private String name;
	private Address address;
	private List<String> arr;

	@Data
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Address {

		private String street;
		private String city;
		private String country;
		private List<String> arr;

	}

}
