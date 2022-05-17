package com.gusrubin.lab.java.lampada;

public class LampadaApplication {

	public static void main(String[] args) {
		System.out.println("Starting application");
		
		Lampada lampadaAmarela = new LampadaAmarela();
		Ambiente ambienteQuente = new Ambiente(lampadaAmarela);
		ambienteQuente.switchState();
		ambienteQuente.switchState();
		ambienteQuente.switchState();
		
		Lampada lampadaBranca = new LampadaBranca();
		Ambiente ambienteFrio = new Ambiente(lampadaBranca);
		ambienteFrio.switchState();
		ambienteFrio.switchState();
		ambienteFrio.switchState();		 
	}

}
