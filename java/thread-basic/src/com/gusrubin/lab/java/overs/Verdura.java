package com.gusrubin.lab.java.overs;

public class Verdura extends Alimento {
	
	@Override
	public void tipo() {
		System.out.print("Verdura");
	}
	
	public void tipo(String classe) {
		System.out.print("Alimento de classe " + classe);
	}

}
