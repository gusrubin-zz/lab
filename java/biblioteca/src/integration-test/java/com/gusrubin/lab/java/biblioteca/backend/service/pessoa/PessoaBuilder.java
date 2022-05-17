package com.gusrubin.lab.java.biblioteca.backend.service.pessoa;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import com.gusrubin.lab.java.biblioteca.backend.repository.pessoa.Pessoa;

public class PessoaBuilder {

	private static final Integer PESSOA_1_ID = 2;

	private static final String PESSOA_1_NOME = "João";

	private static final Integer PESSOA_2_ID = 2;

	private static final String PESSOA_2_NOME = "Maria";

	public static Pessoa buildEntityPessoa1() {
		Pessoa entity = new Pessoa(PESSOA_1_NOME);
		entity.setId(PESSOA_1_ID);
		return entity;
	}

	public static Pessoa buildEntityPessoa2() {
		Pessoa entity = new Pessoa(PESSOA_2_NOME);
		entity.setId(PESSOA_2_ID);
		return entity;
	}

	public static Pessoa buildEntityPessoaAleatoria() {
		Integer id = RandomUtils.nextInt(PESSOA_2_ID + 1, 10000000);
		String nome = RandomStringUtils.randomAlphabetic(5, 20);
		Pessoa entity = new Pessoa(nome);
		entity.setId(id);
		return entity;
	}

}
