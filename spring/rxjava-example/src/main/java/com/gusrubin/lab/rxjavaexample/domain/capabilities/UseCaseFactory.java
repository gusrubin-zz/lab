package com.gusrubin.lab.rxjavaexample.domain.capabilities;

public class UseCaseFactory {

	public static GetCapabilitiesUseCase getCapabilitiresUseCase(
			GetJavaVersionPortOut getJavaVersionPortOut,
			GetNetworkSpeedPortOut getNetworkSpeedPortOut) {
		return new Analyzer(getJavaVersionPortOut, getNetworkSpeedPortOut);
	}

}
