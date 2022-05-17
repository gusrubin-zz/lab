package com.gusrubin.lab.rxjavaexample.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

import com.gusrubin.lab.rxjavaexample.domain.capabilities.Capabilities;
import com.gusrubin.lab.rxjavaexample.domain.capabilities.GetCapabilitiesUseCase;
import com.gusrubin.lab.rxjavaexample.domain.capabilities.UseCaseFactory;

import io.reactivex.Single;
import io.reactivex.observers.TestObserver;

class GetCapabilitiesUseCaseTest {

	@Test
	void ok() throws Throwable {
		String javaVersion = randomString();
		Long networkSpeed = randomLong();
		GetCapabilitiesUseCase useCase = UseCaseFactory.getCapabilitiresUseCase(
				() -> Single.just(javaVersion),
				() -> Single.just(networkSpeed));

		TestObserver<Capabilities> useCaseObserver = useCase.getCapabilities().test();
		assertTrue(useCaseObserver.await(100, TimeUnit.MILLISECONDS));

		useCaseObserver.assertResult(new Capabilities(javaVersion, networkSpeed));
	}

	private String randomString() {
		return UUID.randomUUID().toString();
	}

	private Long randomLong() {
		return ThreadLocalRandom.current().nextLong();
	}

}
