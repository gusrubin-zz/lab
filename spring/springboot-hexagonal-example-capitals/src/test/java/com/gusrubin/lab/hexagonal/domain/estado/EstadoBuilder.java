package com.gusrubin.lab.hexagonal.domain.estado;

/**
 * @author Gustavo Rubin
 */

public class EstadoBuilder {

	private static final Long DEFAULT_ID = 1L;
	private static final String DEFAULT_NOME = "Tocantins";
	private static final String DEFAULT_UF = "TO";
	private static final String DEFAULT_CAPITAL = "Palmas";

	public static Estado buildDefault() {
		return Estado.builder().id(DEFAULT_ID).nome(DEFAULT_NOME).uf(DEFAULT_UF).capital(DEFAULT_CAPITAL).build();
	}

}
