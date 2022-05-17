package com.gusrubin.lab.rxjavaexample.domain.exchange;

import io.reactivex.Single;

public interface FromBridgeToDatabaseUseCase {
	
	Single<String> perform();

}
