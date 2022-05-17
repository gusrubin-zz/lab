package com.gusrubin.lab.java.biblioteca.backend.service.livro;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import com.gusrubin.lab.java.biblioteca.backend.repository.livro.Livro;

public class LivroBuilder {

	private static final Integer LIVRO_1_ID = 1;

	private static final String LIVRO_1_AUTOR = "Machado de Assis";

	private static final String LIVRO_1_TITULO = "Dom Casmurro";

	private static final Integer LIVRO_1_EXEMPLARES_EM_ACERVO = 2;

	private static final Integer LIVRO_2_ID = 2;

	private static final String LIVRO_2_AUTOR = "Clarice Lispector";

	private static final String LIVRO_2_TITULO = "A Hora da Estrela";

	private static final Integer LIVRO_2_EXEMPLARES_EM_ACERVO = 2;

	public static Livro buildEntityLivro1() {
		Livro entity = new Livro(LIVRO_1_TITULO, LIVRO_1_AUTOR, LIVRO_1_EXEMPLARES_EM_ACERVO);
		entity.setId(LIVRO_1_ID);
		return entity;
	}

	public static Livro buildEntityLivro2() {
		Livro entity = new Livro(LIVRO_2_TITULO, LIVRO_2_AUTOR, LIVRO_2_EXEMPLARES_EM_ACERVO);
		entity.setId(LIVRO_2_ID);
		return entity;
	}

	public static Livro buildEntityLivroAleatorio() {
		Integer id = RandomUtils.nextInt(LIVRO_2_ID + 1, 10000000);
		String autor = RandomStringUtils.randomAlphabetic(5, 20);
		String titulo = RandomStringUtils.randomAlphabetic(5, 20);
		Livro entity = new Livro(titulo, autor);
		entity.setId(id);
		return entity;
	}

}
