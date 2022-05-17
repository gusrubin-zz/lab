package com.gusrubin.lab.rxjavaexample.domain.capabilities;

import io.reactivex.Single;

public interface GetNetworkSpeedPortOut {
	
	Single<Long> getNetworkSpeed();

}
