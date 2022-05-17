package com.gusrubin.lab.java.biblioteca.backend.service.pessoa;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import com.gusrubin.lab.java.biblioteca.backend.repository.emprestimo.Emprestimo;
import com.gusrubin.lab.java.biblioteca.backend.repository.emprestimo.EmprestimoRepository;
import com.gusrubin.lab.java.biblioteca.backend.repository.livro.Livro;
import com.gusrubin.lab.java.biblioteca.backend.repository.livro.LivroRepository;
import com.gusrubin.lab.java.biblioteca.backend.repository.pessoa.Pessoa;
import com.gusrubin.lab.java.biblioteca.backend.repository.pessoa.PessoaRepository;
import com.gusrubin.lab.java.biblioteca.backend.service.emprestimo.EmprestimoService;
import com.gusrubin.lab.java.biblioteca.backend.service.livro.LivroBuilder;
import com.gusrubin.lab.java.biblioteca.backend.service.livro.LivroService;

class PessoaServiceTests {

	private final EmprestimoRepository emprestimoRepository = new EmprestimoRepository(true);

	private final LivroRepository livroRepository = new LivroRepository(true);

	private final PessoaRepository pessoaRepository = new PessoaRepository(true);

	private final LivroService livroService = new LivroService(livroRepository, emprestimoRepository);

	private final PessoaService service = new PessoaService(pessoaRepository, emprestimoRepository);

	private final EmprestimoService emprestimoService = new EmprestimoService(emprestimoRepository, livroService,
			service);

	private List<Integer> idsEmprestimoParaDeletar = new ArrayList<>();

	private List<Integer> idsLivroParaDeletar = new ArrayList<>();

	private List<Integer> idsParaDeletar = new ArrayList<>();

	@AfterEach
	void cleanDatabase() {
		idsEmprestimoParaDeletar.stream().forEach(emprestimoRepository::delete);
		idsLivroParaDeletar.stream().forEach(livroService::exclui);
		idsParaDeletar.stream().forEach(service::exclui);
	}

	@Test
	void deveCadastrarNovaPessoaComSucesso() {
		Pessoa pessoa = PessoaBuilder.buildEntityPessoa1();

		Pessoa pessoaCadastrada = service.cadastra(pessoa);

		assertEquals(pessoa, pessoaCadastrada);

		idsParaDeletar.add(pessoaCadastrada.getId());
	}

	@Test
	void deveFalharAoTentarCadastrarNovaPessoaSemNome() {
		Pessoa pessoa = PessoaBuilder.buildEntityPessoa1();
		pessoa.setNome(null);

		assertThrows(IllegalArgumentException.class, () -> service.cadastra(pessoa));
	}

	@Test
	void deveConsultarPessoaCadastradaComSucesso() {
		Pessoa pessoa = PessoaBuilder.buildEntityPessoa1();
		Pessoa pessoaCadastrada = service.cadastra(pessoa);

		Pessoa pessoaConsultado = service.buscaPorId(pessoaCadastrada.getId());

		assertEquals(pessoaCadastrada.getId(), pessoaConsultado.getId());
		assertEquals(pessoaCadastrada.getNome(), pessoaConsultado.getNome());

		idsParaDeletar.add(pessoaCadastrada.getId());
	}

	@Test
	void deveFalharAoTentarConsultarPessoaComIdNaoCadastrado() {
		assertThrows(IllegalStateException.class, () -> service.buscaPorId(1234));
	}

	@Test
	void deveFalharAoTentarConsultarPessoaComNomeNaoCadastrado() {
		assertThrows(IllegalStateException.class, () -> service.buscaPorNome("xxxx"));
	}

	@Test
	void deveAtualizarPessoaCadastradaComSucesso() {
		Pessoa pessoa = PessoaBuilder.buildEntityPessoa1();
		Pessoa pessoaCadastrada = service.cadastra(pessoa);
		pessoa.setNome("Novo Nome");

		Pessoa pessoaAtualizada = service.atualiza(pessoa);

		assertEquals(pessoaCadastrada.getId(), pessoaAtualizada.getId());
		assertEquals(pessoaCadastrada.getNome(), pessoaAtualizada.getNome());

		idsParaDeletar.add(pessoaCadastrada.getId());
	}

	@Test
	void deveExcluirPessoaCadastradaComSucesso() {
		Pessoa pessoa = PessoaBuilder.buildEntityPessoa1();
		Pessoa pessoaCadastrada = service.cadastra(pessoa);

		service.exclui(pessoaCadastrada.getId());

		assertThrows(IllegalStateException.class, () -> service.exclui(pessoa.getId()));
	}

	@Test
	void deveFalharAoTentarExcluirPessoaComEmprestimosAtivos() {
		Pessoa pessoa = PessoaBuilder.buildEntityPessoa1();
		Pessoa pessoaCadastrada = service.cadastra(pessoa);
		Livro livro = LivroBuilder.buildEntityLivro1();
		Livro livroCadastrado = livroService.cadastra(livro);
		Emprestimo emprestimo = emprestimoService.novoEmprestimo(pessoaCadastrada.getId(), livro.getId());

		assertThrows(IllegalStateException.class, () -> service.exclui(pessoaCadastrada.getId()));

		idsEmprestimoParaDeletar.add(emprestimo.getId());
		idsLivroParaDeletar.add(livroCadastrado.getId());
		idsParaDeletar.add(pessoaCadastrada.getId());
	}

}
