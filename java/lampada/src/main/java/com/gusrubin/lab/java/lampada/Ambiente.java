package com.gusrubin.lab.java.lampada;

public class Ambiente {
	
	Lampada lampada;
	
	public Ambiente(Lampada lampada) {
		this.lampada = lampada;
	}
	
	public void switchState() {
		if (lampada instanceof LampadaBase) {
			if (((LampadaBase) lampada).getState()) {
				lampada.off(); 
			} else {
				lampada.on();
			}
		}
	}

}
