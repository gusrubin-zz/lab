package com.gusrubin.lab.java.lampada;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AmbienteTests {

	@Test
	void shouldSwitchStatesOfLampadaAmarela() {
		LampadaAmarela lampadaAmarela = new LampadaAmarela();
		Ambiente ambiente = new Ambiente(lampadaAmarela);

		// Check if initial state is off
		assertFalse(lampadaAmarela.getState());

		// Check if switch state to on
		ambiente.switchState();
		assertTrue(lampadaAmarela.getState());

		// Check if switch state to off
		ambiente.switchState();
		assertFalse(lampadaAmarela.getState());
	}

	@Test
	void shouldSwitchStatesOfLampadaBranca() {
		LampadaBranca lampadaBranca = new LampadaBranca();
		Ambiente ambiente = new Ambiente(lampadaBranca);

		// Check if initial state is off
		assertFalse(lampadaBranca.getState());

		// Check if switch state to on
		ambiente.switchState();
		assertTrue(lampadaBranca.getState());

		// Check if switch state to off
		ambiente.switchState();
		assertFalse(lampadaBranca.getState());
	}

}
