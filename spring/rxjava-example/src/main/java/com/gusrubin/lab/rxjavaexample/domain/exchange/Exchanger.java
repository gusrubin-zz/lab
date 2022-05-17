package com.gusrubin.lab.rxjavaexample.domain.exchange;

import org.springframework.stereotype.Service;

import io.reactivex.Single;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class Exchanger implements FromBridgeToDatabaseUseCase {

	@Override
	public Single<String> perform() {
		log.info("Perform bridge to database");
		return Single.just(TransferExample.transfer());
	}

}
