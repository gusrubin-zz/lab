package com.gusrubin.lab.java.threads;

public class MultithreadingApp {
	
	private static final Long SLEEP_PERIOD_FOR_EVEN_NUMBER = 500L;
	
	private static final Long SLEEP_PERIOD_FOR_ODD_NUMBER = 100L;

	public static void main(String[] args) {
		System.out.println("Starting application");

		new RandomEvenNumber(SLEEP_PERIOD_FOR_EVEN_NUMBER).start();
		
		new RandomOddNumber(SLEEP_PERIOD_FOR_ODD_NUMBER).start();		
	}

}
