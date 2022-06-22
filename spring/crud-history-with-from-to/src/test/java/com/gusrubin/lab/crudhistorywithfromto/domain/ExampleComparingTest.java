package com.gusrubin.lab.crudhistorywithfromto.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gusrubin.lab.crudhistorywithfromto.domain.history.ActionLogDiff;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(MockitoExtension.class)
class ExampleComparingTest {

	@Test
	void test() throws IllegalArgumentException, IllegalAccessException, JsonProcessingException, SecurityException {
		CustomerForTests.Address addressA = CustomerForTests.Address.builder()
		// @formatter:off
				.street("Street A")
				.city("City A")
				.country("Country A")
				.arr(List.of("1", "2", "3"))
				.build();
		// @formatter:on

		CustomerForTests customerA = CustomerForTests.builder()
		// @formatter:off
				.id(1L)
				.name("CustomerA")
				.address(addressA)
				.arr(List.of("1", "2"))
				.build();
		// @formatter:on

		CustomerForTests.Address addressB = CustomerForTests.Address.builder()
		// @formatter:off
				.street("Street A")
				.city("City A")
				.country("Country A")
				.arr(List.of("1", "2"))
				.build();
		// @formatter:on

		CustomerForTests customerB = CustomerForTests.builder()
		// @formatter:off
				.id(1L)
				.name("CustomerA")
				.address(addressB)
				.arr(List.of("1", "2"))
				.build();
		// @formatter:on		

//		Customer customerDiff = customerA.diff(customerB);

		ActionLogDiff<CustomerForTests> ad = new ActionLogDiff<>();
//		String customerDiff = ad.diff(customerA, customerB);
		String customerDiff = ad.diffJson(customerA, customerB);
		log.info(customerDiff.toString());

		assertTrue(true);
	}

}
