package com.gusrubin.lab.rxjavaexample.application;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gusrubin.lab.rxjavaexample.domain.capabilities.Capabilities;
import com.gusrubin.lab.rxjavaexample.domain.capabilities.GetCapabilitiesUseCase;
import com.gusrubin.lab.rxjavaexample.domain.capabilities.UseCaseFactory;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

@RestController
public class AnalyzerController {

	@GetMapping("/")
	public Single<ResponseEntity<Capabilities>> getCapabilities() {

		String javaVersion = randomString();
		Long networkSpeed = randomLong();
		GetCapabilitiesUseCase useCase = UseCaseFactory.getCapabilitiresUseCase(
				() -> Single.just(javaVersion),
				() -> Single.just(networkSpeed));

//		Observer<Capabilities> obs = useCase.getCapabilities().delay(10, TimeUnit.SECONDS).test();

		return useCase.getCapabilities().subscribeOn(Schedulers.io())
				.map(ResponseEntity::ok);

	}

	private String randomString() {
		return UUID.randomUUID().toString();
	}

	private Long randomLong() {
		return ThreadLocalRandom.current().nextLong();
	}

}
