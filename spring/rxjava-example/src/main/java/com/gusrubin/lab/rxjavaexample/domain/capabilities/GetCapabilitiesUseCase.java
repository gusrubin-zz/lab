package com.gusrubin.lab.rxjavaexample.domain.capabilities;

import io.reactivex.Single;

public interface GetCapabilitiesUseCase {
	
	Single<Capabilities> getCapabilities();

}
