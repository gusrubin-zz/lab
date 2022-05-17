package com.gusrubin.lab.java.lampada;

public abstract class LampadaBase implements Lampada {
	
	protected boolean state;
	
	protected String nomeLampada;
	
	public LampadaBase(String nomeLampada) {
		this.nomeLampada = nomeLampada;
		off();
	}
	
	@Override
	public void on() {
		state = true;
		System.out.println("Lampada " +  nomeLampada + " ligada");
	}

	@Override
	public void off() {
		state = false;
		System.out.println("Lampada " +  nomeLampada + " desligada");
	}
	
	public boolean getState() {
		return state;
	}

}
