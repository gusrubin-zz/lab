package com.gusrubin.lab.java.test.overs;

import org.junit.jupiter.api.Test;

import com.gusrubin.lab.java.overs.Alimento;
import com.gusrubin.lab.java.overs.Verdura;

class AlimentoTests {

	@Test
	void callTipoAlimento() {
		Alimento alimento = new Alimento();
		
		alimento.tipo();
	}
	
	@Test
	void callTipoVerdura() {
		Verdura verdura = new Verdura();
		
		verdura.tipo();
	}
	
	@Test
	void callTipoVerduraOverload() {
		Verdura verdura = new Verdura();
		
		verdura.tipo("Alface");
	}

}
