package com.gusrubin.lab.hexagonal.domain.customer;

/**
 * @author Gustavo Rubin
 */

public class CustomerBuilder {

	private static final Long DEFAULT_ID = 1L;
	private static final String DEFAULT_NAME = "Fulano";
	private static final String DEFAULT_EMAIL = "fulano@test.com";

	public static Customer buildDefault() {
		return Customer.builder().id(DEFAULT_ID).name(DEFAULT_NAME).email(DEFAULT_EMAIL).build();
	}

}
