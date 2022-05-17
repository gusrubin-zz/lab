package com.gusrubin.lab.java.biblioteca.backend.service.livro;

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
import com.gusrubin.lab.java.biblioteca.backend.service.pessoa.PessoaBuilder;
import com.gusrubin.lab.java.biblioteca.backend.service.pessoa.PessoaService;

class LivroServiceTests {

	private final EmprestimoRepository emprestimoRepository = new EmprestimoRepository(true);
	private List<Integer> idsEmprestimoParaDeletar = new ArrayList<>();

	private final LivroRepository livroRepository = new LivroRepository(true);
	private List<Integer> idsParaDeletar = new ArrayList<>();

	private final PessoaRepository pessoaRepository = new PessoaRepository(true);
	private List<Integer> idsPessoaParaDeletar = new ArrayList<>();

	private final LivroService service = new LivroService(livroRepository, emprestimoRepository);

	private final PessoaService pessoaService = new PessoaService(pessoaRepository, emprestimoRepository);

	private final EmprestimoService emprestimoService = new EmprestimoService(emprestimoRepository, service,
			pessoaService);

	@AfterEach
	void cleanDatabase() {
		idsPessoaParaDeletar.stream().forEach(pessoaRepository::delete);
		idsEmprestimoParaDeletar.stream().forEach(emprestimoRepository::delete);
		idsParaDeletar.stream().forEach(service::exclui);
	}

	@Test
	void deveCadastrarNovoLivroComSucesso() {
		Livro livro = LivroBuilder.buildEntityLivro1();

		Livro livroCadastrado = service.cadastra(livro);
		idsParaDeletar.add(livroCadastrado.getId());

		assertEquals(livro, livroCadastrado);	
	}

	@Test
	void deveFalharAoTentarCadastrarNovoLivroSemTitulo() {
		Livro livro = LivroBuilder.buildEntityLivro1();
		livro.setTitulo(null);

		assertThrows(IllegalArgumentException.class, () -> service.cadastra(livro));
	}

	@Test
	void deveFalharAoTentarCadastrarNovoLivroSemAutor() {
		Livro livro = LivroBuilder.buildEntityLivro1();
		livro.setAutor(null);

		assertThrows(IllegalArgumentException.class, () -> service.cadastra(livro));
	}

	@Test
	void deveConsultarLivroCadastradoComSucesso() {
		Livro livro = LivroBuilder.buildEntityLivro1();
		Livro livroCadastrado = service.cadastra(livro);
		idsParaDeletar.add(livroCadastrado.getId());

		Livro livroConsultado = service.buscaPorId(livroCadastrado.getId());

		assertEquals(livroCadastrado.getId(), livroConsultado.getId());
		assertEquals(livroCadastrado.getTitulo(), livroConsultado.getTitulo());
		assertEquals(livroCadastrado.getAutor(), livroConsultado.getAutor());		
	}

	@Test
	void deveFalharAoTentarConsultarLivroComIdNaoCadastrado() {
		assertThrows(IllegalStateException.class, () -> service.buscaPorId(1234));
	}

	@Test
	void deveFalharAoTentarConsultarLivroComAutorNaoCadastrado() {
		assertThrows(IllegalStateException.class, () -> service.buscaPorAutor("xxxx"));
	}

	@Test
	void deveFalharAoTentarConsultarLivroComTituloNaoCadastrado() {
		assertThrows(IllegalStateException.class, () -> service.buscaPorTitulo("xxxx"));
	}

	@Test
	void deveAtualizarLivroCadastradoComSucesso() {
		Livro livro = LivroBuilder.buildEntityLivro1();
		Livro livroCadastrado = service.cadastra(livro);
		idsParaDeletar.add(livroCadastrado.getId());
		livro.setAutor("Novo Autor");

		Livro livroAtualizado = service.atualiza(livro);

		assertEquals(livroCadastrado.getId(), livroAtualizado.getId());
		assertEquals(livroCadastrado.getTitulo(), livroAtualizado.getTitulo());
		assertEquals(livroCadastrado.getAutor(), livroAtualizado.getAutor());		
	}

	@Test
	void deveFalharAoTentarAtualizarLivroCadastradoComNumeroDeExemplaresMenorDoQueEmprestimosAtivos() {
		Livro livro = LivroBuilder.buildEntityLivro1();
		Livro livroCadastrado = service.cadastra(livro);
		idsParaDeletar.add(livroCadastrado.getId());
		Pessoa pessoa1 = PessoaBuilder.buildEntityPessoa1();
		Pessoa pessoaCadastrada1 = pessoaService.cadastra(pessoa1);
		idsPessoaParaDeletar.add(pessoaCadastrada1.getId());
		Emprestimo emprestimo1 = emprestimoService.novoEmprestimo(pessoaCadastrada1.getId(), livro.getId());
		idsEmprestimoParaDeletar.add(emprestimo1.getId());
		Pessoa pessoa2 = PessoaBuilder.buildEntityPessoa2();
		Pessoa pessoaCadastrada2 = pessoaService.cadastra(pessoa2);
		idsPessoaParaDeletar.add(pessoaCadastrada2.getId());
		Emprestimo emprestimo2 = emprestimoService.novoEmprestimo(pessoaCadastrada2.getId(), livro.getId());
		idsEmprestimoParaDeletar.add(emprestimo2.getId());

		livro.setExemplaresEmAcervo(1);
		assertThrows(IllegalStateException.class, () -> service.atualiza(livro));
	}

	@Test
	void deveExcluirLivroCadastradoComSucesso() {
		Livro livro = LivroBuilder.buildEntityLivro1();
		Livro livroCadastrado = service.cadastra(livro);

		service.exclui(livroCadastrado.getId());

		assertThrows(IllegalStateException.class, () -> service.buscaPorId(livro.getId()));
	}

	@Test
	void deveFalharAoTentarExcluirLivroNaoCadastrado() {
		Livro livro = LivroBuilder.buildEntityLivro1();

		assertThrows(IllegalStateException.class, () -> service.exclui(livro.getId()));
	}

	@Test
	void deveFalharAoTentarExcluirLivroComEmprestimosAtivos() {
		Livro livro = LivroBuilder.buildEntityLivro1();
		Livro livroCadastrado = service.cadastra(livro);
		idsParaDeletar.add(livroCadastrado.getId());
		Pessoa pessoa1 = PessoaBuilder.buildEntityPessoa1();
		Pessoa pessoaCadastrada1 = pessoaService.cadastra(pessoa1);
		idsPessoaParaDeletar.add(pessoaCadastrada1.getId());
		Emprestimo emprestimo = emprestimoService.novoEmprestimo(pessoaCadastrada1.getId(),
				livro.getId());
		idsEmprestimoParaDeletar.add(emprestimo.getId());

		assertThrows(IllegalStateException.class, () -> service.exclui(livro.getId()));
	}

}
