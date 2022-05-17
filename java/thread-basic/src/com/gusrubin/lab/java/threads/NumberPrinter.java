package com.gusrubin.lab.java.threads;

public abstract class NumberPrinter extends Thread {
	
	Long sleepPeriod;
	
	protected abstract Integer generateNumber();
	
	public NumberPrinter(Long sleepPeriod) {
		this.sleepPeriod = sleepPeriod;
	}
	
	@Override
	public void run() {

		while (true) {
			System.out.println(generateNumber());
			
			try {
				Thread.sleep(sleepPeriod);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}

}
