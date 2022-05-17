package com.gusrubin.lab.docker.backend;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("local")
class BackendApplicationTests {

	@Test
	void contextLoads() {
		assertTrue(true);
	}

}
