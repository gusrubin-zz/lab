package com.gusrubin.lab.helloworldcommandline;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HelloWorldCommandlineApplicationTests {
	
	private static Logger LOG = LoggerFactory
		      .getLogger(HelloWorldCommandlineApplicationTests.class);

	@Test
	void contextLoads() {
		LOG.info("default test");
		assertTrue(true);
	}

}
