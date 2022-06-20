package com.gusrubin.lab.temporaltables.domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.startsWith;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;

import com.gusrubin.lab.crudhistory.domain.history.ActionLog;
import com.gusrubin.lab.crudhistory.domain.history.ActionType;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(MockitoExtension.class)
class ExampleMatcherTest {

	@Test
	void test() {
		ActionLog actionLog = ActionLog.builder()
		// @formatter:off
				.occurredAt(LocalDateTime.now())
				.username("testuser")
				.context("customer")
				.action(ActionType.CREATE)
				.commandArgs("Many args")
				.commandResult("Many results")
				.build();
		// @formatter:on

		ExampleMatcher matcher = ExampleMatcher.matchingAll()
		// @formatter:off
				.withMatcher("username", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
		// @formatter:on		

		Example<ActionLog> example = Example.of(actionLog, matcher);
//		log.info(example);

		assertTrue(true);
	}

}
