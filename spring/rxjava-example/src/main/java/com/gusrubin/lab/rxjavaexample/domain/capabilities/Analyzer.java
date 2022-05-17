package com.gusrubin.lab.rxjavaexample.domain.capabilities;

import com.gusrubin.lab.rxjavaexample.domain.capabilities.Capabilities.CapabilitiesBuilder;

import io.reactivex.Single;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Analyzer implements GetCapabilitiesUseCase {
	
	private final GetJavaVersionPortOut getJavaVersionPortOut;
	private final GetNetworkSpeedPortOut getNetworkSpeedPortOut;

	@Override
	public Single<Capabilities> getCapabilities() {
		return Single.just(Capabilities.builder())
				.flatMap(this::getJavaVersion)
				.flatMap(this::getNetworkSpeed)
				.map(CapabilitiesBuilder::build);
	}
	
	private Single<CapabilitiesBuilder> getJavaVersion(CapabilitiesBuilder builder) {
	    return getJavaVersionPortOut
	      .getJavaVersion()
	      .map(builder::javaVersion);
	}

	private Single<CapabilitiesBuilder> getNetworkSpeed(CapabilitiesBuilder builder) {
	    return getNetworkSpeedPortOut
	      .getNetworkSpeed()
	      .map(builder::networkSpeed);
	}

}
