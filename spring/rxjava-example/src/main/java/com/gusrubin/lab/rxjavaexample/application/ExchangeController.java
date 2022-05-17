package com.gusrubin.lab.rxjavaexample.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gusrubin.lab.rxjavaexample.domain.exchange.Exchanger;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

@RestController
@RequestMapping("/exchange")
public class ExchangeController {

	private final Exchanger exchanger;

	@Autowired
	public ExchangeController(Exchanger exchanger) {
		this.exchanger = exchanger;
	}

	@GetMapping("/to-bridge")
	public ResponseEntity<String> toBridge() {
		
		exchanger.perform();
		
		return ResponseEntity.ok().build();

//		return exchanger.perform().subscribeOn(Schedulers.io()).map(ResponseEntity::ok);
	}

}
