package com.gusrubin.lab.java.biblioteca.backend.service.emprestimo;

import java.time.LocalDate;

import org.apache.commons.lang3.RandomUtils;

import com.gusrubin.lab.java.biblioteca.backend.repository.emprestimo.Emprestimo;
import com.gusrubin.lab.java.biblioteca.backend.repository.livro.Livro;
import com.gusrubin.lab.java.biblioteca.backend.repository.pessoa.Pessoa;
import com.gusrubin.lab.java.biblioteca.backend.service.livro.LivroBuilder;
import com.gusrubin.lab.java.biblioteca.backend.service.pessoa.PessoaBuilder;

public class EmprestimoBuilder {

	private static final Integer EMPRESTIMO_1_ID = 1;

	private static final Livro LIVRO_1 = LivroBuilder.buildEntityLivro1();

	private static final Pessoa PESSOA_1 = PessoaBuilder.buildEntityPessoa1();

	private static final LocalDate DATA_EMPRESTIMO_1 = LocalDate.of(2020, 01, 01);

	private static final Integer EMPRESTIMO_2_ID = 2;

	private static final Livro LIVRO_2 = LivroBuilder.buildEntityLivro2();

	private static final Pessoa PESSOA_2 = PessoaBuilder.buildEntityPessoa2();

	private static final LocalDate DATA_EMPRESTIMO_2 = LocalDate.of(2020, 02, 01);

	public static Emprestimo buildEntityEmprestimo1() {
		Emprestimo entity = new Emprestimo(LIVRO_1.getId(), PESSOA_1.getId());
		entity.setId(EMPRESTIMO_1_ID);
		entity.setDataEmprestimo(DATA_EMPRESTIMO_1);
		return entity;
	}

	public static Emprestimo buildEntityEmprestimo2() {
		Emprestimo entity = new Emprestimo(LIVRO_2.getId(), PESSOA_2.getId());
		entity.setId(EMPRESTIMO_2_ID);
		entity.setDataEmprestimo(DATA_EMPRESTIMO_2);
		return entity;
	}

	public static Emprestimo buildEntityEmprestimoAleatorio() {
		Integer id = RandomUtils.nextInt(EMPRESTIMO_2_ID + 1, 10000000);
		Livro livro = LivroBuilder.buildEntityLivroAleatorio();
		Pessoa pessoa = PessoaBuilder.buildEntityPessoaAleatoria();
		Emprestimo entity = new Emprestimo(livro.getId(), pessoa.getId());
		entity.setId(id);
		entity.setDataEmprestimo(DATA_EMPRESTIMO_1);
		return entity;
	}

}
