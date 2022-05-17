package com.gusrubin.lab.rxjavaexample.domain.exchange;

import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TransferExample {
	
	public static String transfer() {
		try {
			TimeUnit.SECONDS.sleep(4);
			log.info("done");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "transfer";
	}

}
