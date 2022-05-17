package com.gusrubin.lab.java.threads;

import java.util.Random;

public class RandomOddNumber extends NumberPrinter {

	public RandomOddNumber(Long sleepPeriod) {
		super(sleepPeriod);
	}

	@Override
	protected Integer generateNumber() {
		Integer randomNumber = new Random().nextInt();
		while (randomNumber % 2 == 0) {
			randomNumber = new Random().nextInt();
		}		
		return randomNumber;
	}

}
