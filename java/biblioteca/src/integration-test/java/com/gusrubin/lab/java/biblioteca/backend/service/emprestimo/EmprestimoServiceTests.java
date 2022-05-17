package com.gusrubin.lab.java.biblioteca.backend.service.emprestimo;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Period;
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
import com.gusrubin.lab.java.biblioteca.backend.service.livro.LivroBuilder;
import com.gusrubin.lab.java.biblioteca.backend.service.livro.LivroService;
import com.gusrubin.lab.java.biblioteca.backend.service.pessoa.PessoaBuilder;
import com.gusrubin.lab.java.biblioteca.backend.service.pessoa.PessoaService;

class EmprestimoServiceTests {

	private static final String TITULO_DO_LIVRO_1 = "Programando em Java";

	private static final String AUTOR_DO_LIVRO_1 = "Gustavo Rubin";

	private static final String NOME_DA_PESSOA_1 = "Jose";

	private final LivroRepository livroRepository = new LivroRepository(true);
	private List<Integer> idsLivroParaDeletar = new ArrayList<>();

	private final PessoaRepository pessoaRepository = new PessoaRepository(true);
	private List<Integer> idsPessoaParaDeletar = new ArrayList<>();

	private final EmprestimoRepository emprestimoRepository = new EmprestimoRepository(true);
	private List<Integer> idsParaDeletar = new ArrayList<>();

	private final LivroService livroService = new LivroService(livroRepository, emprestimoRepository);

	private final PessoaService pessoaService = new PessoaService(pessoaRepository, emprestimoRepository);

	private final EmprestimoService service = new EmprestimoService(emprestimoRepository, livroService, pessoaService);

	@AfterEach
	void cleanDatabase() {
		idsParaDeletar.stream().forEach(emprestimoRepository::delete);
		idsLivroParaDeletar.stream().forEach(livroRepository::delete);
		idsPessoaParaDeletar.stream().forEach(pessoaRepository::delete);
	}

	@Test
	void deveCadastrarNovoEmprestimoComSucesso() {
		Livro livro = cadastraLivro();
		idsLivroParaDeletar.add(livro.getId());
		Pessoa pessoa = cadastraPessoa();
		idsPessoaParaDeletar.add(pessoa.getId());

		Emprestimo emprestimoCadastrado = service.novoEmprestimo(pessoa.getId(), livro.getId());
		idsParaDeletar.add(emprestimoCadastrado.getId());

		assertEquals(livro.getId(), emprestimoCadastrado.getIdLivro());
		assertEquals(pessoa.getId(), emprestimoCadastrado.getIdPessoa());
	}

	@Test
	void deveFalharAoTentarCadastrarNovoEmprestimoComPessoaNaoCadastrada() {
		Livro livro = cadastraLivro();
		idsLivroParaDeletar.add(livro.getId());
		Pessoa pessoa = PessoaBuilder.buildEntityPessoa1();

		assertThrows(IllegalStateException.class, () -> service.novoEmprestimo(pessoa.getId(), livro.getId()));
	}

	@Test
	void deveFalharAoTentarCadastrarNovoEmprestimoComLivroNaoCadastrado() {
		Livro livro = LivroBuilder.buildEntityLivro1();
		Pessoa pessoa = cadastraPessoa();
		idsPessoaParaDeletar.add(pessoa.getId());

		assertThrows(IllegalStateException.class, () -> service.novoEmprestimo(pessoa.getId(), livro.getId()));
	}

	@Test
	void deveFalharAoTentarCadastrarNovoEmprestimoParaPessoaComMaximoDeEmprestimosPermitido() {
		Livro livro = cadastraLivro();
		idsLivroParaDeletar.add(livro.getId());
		Pessoa pessoa = cadastraPessoa();
		idsPessoaParaDeletar.add(pessoa.getId());

		for (int i = 1; i <= 5; i++) {
			Emprestimo emprestimoCadastrado = service.novoEmprestimo(pessoa.getId(), livro.getId());
			idsParaDeletar.add(emprestimoCadastrado.getId());
		}

		assertThrows(IllegalStateException.class, () -> service.novoEmprestimo(pessoa.getId(), livro.getId()));
	}

	@Test
	void deveFalharAoTentarCadastrarNovoEmprestimoComLivroSemExemplaresDisponiveis() {
		Livro livro = cadastraLivro();
		idsLivroParaDeletar.add(livro.getId());
		livro.setExemplaresEmAcervo(1);
		livroService.atualiza(livro);
		Pessoa pessoa = cadastraPessoa();
		idsPessoaParaDeletar.add(pessoa.getId());
		Emprestimo emprestimo1 = service.novoEmprestimo(pessoa.getId(), livro.getId());
		idsParaDeletar.add(emprestimo1.getId());

		assertThrows(IllegalStateException.class, () -> service.novoEmprestimo(pessoa.getId(), livro.getId()));
	}

	@Test
	void deveRenovarEmprestimoComSucesso() {
		Livro livro = cadastraLivro();
		idsLivroParaDeletar.add(livro.getId());
		Pessoa pessoa = cadastraPessoa();
		idsPessoaParaDeletar.add(pessoa.getId());
		Emprestimo emprestimoCadastrado = service.novoEmprestimo(pessoa.getId(), livro.getId());
		idsParaDeletar.add(emprestimoCadastrado.getId());

		Emprestimo emprestimoRenovado = service.renovaEmprestimo(emprestimoCadastrado.getId());

		assertEquals(emprestimoCadastrado.getIdLivro(), emprestimoRenovado.getIdLivro());
		assertEquals(emprestimoCadastrado.getIdPessoa(), emprestimoRenovado.getIdPessoa());
		assertNull(emprestimoCadastrado.getDataRenovacao());
		assertNotNull(emprestimoRenovado.getDataRenovacao());

		assertTrue(true);
	}

	@Test
	void deveFalharAoTentarRenovarEmprestimoNaoCadastrado() {
		assertThrows(IllegalStateException.class, () -> service.renovaEmprestimo(10));
	}

	@Test
	void deveFalharAoTentarRenovarEmprestimoJaRenovado() {
		Livro livro = cadastraLivro();
		idsLivroParaDeletar.add(livro.getId());
		Pessoa pessoa = cadastraPessoa();
		idsPessoaParaDeletar.add(pessoa.getId());
		Emprestimo emprestimoCadastrado = service.novoEmprestimo(pessoa.getId(), livro.getId());
		idsParaDeletar.add(emprestimoCadastrado.getId());
		service.renovaEmprestimo(emprestimoCadastrado.getId());

		assertThrows(IllegalStateException.class, () -> service.renovaEmprestimo(emprestimoCadastrado.getId()));
	}

	@Test
	void deveFalharAoTentarRenovarEmprestimoDePessoaComDevolucaoEmAtraso() {
		Livro livro = cadastraLivro();
		idsLivroParaDeletar.add(livro.getId());
		Pessoa pessoa = cadastraPessoa();
		idsPessoaParaDeletar.add(pessoa.getId());
		Emprestimo emprestimoCadastrado = service.novoEmprestimo(pessoa.getId(), livro.getId());
		idsParaDeletar.add(emprestimoCadastrado.getId());

		emprestimoCadastrado.setDataEmprestimo(emprestimoCadastrado.getDataEmprestimo().minus(Period.ofDays(8)));
		emprestimoRepository.save(emprestimoCadastrado);

		assertThrows(IllegalStateException.class, () -> service.renovaEmprestimo(emprestimoCadastrado.getId()));
	}

	@Test
	void deveListarTodosOsEmprestimosComSucesso() {
		Livro livro = cadastraLivro();
		idsLivroParaDeletar.add(livro.getId());
		Pessoa pessoa = cadastraPessoa();
		idsPessoaParaDeletar.add(pessoa.getId());

		for (int i = 1; i < 5; i++) {
			Emprestimo emprestimoCadastrado = service.novoEmprestimo(pessoa.getId(), livro.getId());
			idsParaDeletar.add(emprestimoCadastrado.getId());
		}

		assertEquals(4, service.livrosEmprestados().size());
	}

	@Test
	void deveListarEmprestimosPorPessoa() {
		Livro livro = cadastraLivro();
		idsLivroParaDeletar.add(livro.getId());
		Pessoa pessoa = cadastraPessoa();
		idsPessoaParaDeletar.add(pessoa.getId());

		Emprestimo emprestimoCadastrado = service.novoEmprestimo(pessoa.getId(), livro.getId());
		idsParaDeletar.add(emprestimoCadastrado.getId());

		List<LivroEmprestado> emprestimos = service.livrosEmprestadosPorPessoa(pessoa.getId());

		assertEquals(1, emprestimos.size());
		assertEquals(pessoa.getNome(), emprestimos.get(0).getNomePessoa());
		assertEquals(livro.getTitulo(), emprestimos.get(0).getTituloLivro());
	}

	@Test
	void deveListarEmprestimosPorLivro() {
		Livro livro = cadastraLivro();
		idsLivroParaDeletar.add(livro.getId());
		Pessoa pessoa = cadastraPessoa();
		idsPessoaParaDeletar.add(pessoa.getId());

		Emprestimo emprestimoCadastrado = service.novoEmprestimo(pessoa.getId(), livro.getId());
		idsParaDeletar.add(emprestimoCadastrado.getId());

		List<LivroEmprestado> emprestimos = service.livrosEmprestadosPorLivro(livro.getId());

		assertEquals(1, emprestimos.size());
		assertEquals(pessoa.getNome(), emprestimos.get(0).getNomePessoa());
		assertEquals(livro.getTitulo(), emprestimos.get(0).getTituloLivro());
	}

	@Test
	void deveDevolverEmprestimoComSucesso() {
		Livro livro = cadastraLivro();
		idsLivroParaDeletar.add(livro.getId());
		Pessoa pessoa = cadastraPessoa();
		idsPessoaParaDeletar.add(pessoa.getId());
		Emprestimo emprestimoCadastrado = service.novoEmprestimo(pessoa.getId(), livro.getId());
		idsParaDeletar.add(emprestimoCadastrado.getId());

		Integer multaPorAtraso = service.devolucao(emprestimoCadastrado.getId());

		assertEquals(0, multaPorAtraso);
	}

	@Test
	void deveFalharAoTentarDevolverEmprestimoNaoCadastrado() {
		assertThrows(IllegalStateException.class, () -> service.devolucao(10));
	}

	@Test
	void deveFalharAoTentarDevolverEmprestimoJaDevolvido() {
		Livro livro = cadastraLivro();
		idsLivroParaDeletar.add(livro.getId());
		Pessoa pessoa = cadastraPessoa();
		idsPessoaParaDeletar.add(pessoa.getId());
		Emprestimo emprestimoCadastrado = service.novoEmprestimo(pessoa.getId(), livro.getId());
		idsParaDeletar.add(emprestimoCadastrado.getId());
		service.devolucao(emprestimoCadastrado.getId());

		assertThrows(IllegalStateException.class, () -> service.devolucao(emprestimoCadastrado.getId()));
	}

	@Test
	void deveDevolverEmprestimoECobrarMulta() {
		Livro livro = cadastraLivro();
		idsLivroParaDeletar.add(livro.getId());
		Pessoa pessoa = cadastraPessoa();
		idsPessoaParaDeletar.add(pessoa.getId());
		Emprestimo emprestimoCadastrado = service.novoEmprestimo(pessoa.getId(), livro.getId());
		idsParaDeletar.add(emprestimoCadastrado.getId());
		emprestimoCadastrado.setDataEmprestimo(emprestimoCadastrado.getDataEmprestimo().minus(Period.ofDays(10)));
		emprestimoRepository.save(emprestimoCadastrado);

		Integer multaPorAtraso = service.devolucao(emprestimoCadastrado.getId());

		assertEquals(15, multaPorAtraso);
	}

	@Test
	void deveDevolverEmprestimoRenovadoECobrarMulta() {
		Livro livro = cadastraLivro();
		idsLivroParaDeletar.add(livro.getId());

		pessoaService.listaTodas().stream().forEach(p -> System.out.println(p.getNome()));

		Pessoa pessoa = cadastraPessoa();
		idsPessoaParaDeletar.add(pessoa.getId());
		Emprestimo emprestimoCadastrado = service.novoEmprestimo(pessoa.getId(), livro.getId());
		idsParaDeletar.add(emprestimoCadastrado.getId());
		emprestimoCadastrado = service.renovaEmprestimo(emprestimoCadastrado.getId());
		emprestimoCadastrado.setDataRenovacao(emprestimoCadastrado.getDataRenovacao().minus(Period.ofDays(10)));
		emprestimoRepository.save(emprestimoCadastrado);

		Integer multaPorAtraso = service.devolucao(emprestimoCadastrado.getId());

		assertEquals(15, multaPorAtraso);
	}

	@Test
	void listRankingTop10PessoasQueMaisEmprestam() {
		Livro livro = cadastraLivro();
		idsLivroParaDeletar.add(livro.getId());
		Pessoa pessoa1 = pessoaService.cadastra(new Pessoa("Pessoa1"));
		idsPessoaParaDeletar.add(pessoa1.getId());
		Pessoa pessoa2 = pessoaService.cadastra(new Pessoa("Pessoa2"));
		idsPessoaParaDeletar.add(pessoa2.getId());
		Pessoa pessoa3 = pessoaService.cadastra(new Pessoa("Pessoa3"));
		idsPessoaParaDeletar.add(pessoa3.getId());
		Pessoa pessoa4 = pessoaService.cadastra(new Pessoa("Pessoa4"));
		idsPessoaParaDeletar.add(pessoa4.getId());
		Pessoa pessoa5 = pessoaService.cadastra(new Pessoa("Pessoa5"));
		idsPessoaParaDeletar.add(pessoa5.getId());
		Pessoa pessoa6 = pessoaService.cadastra(new Pessoa("Pessoa6"));
		idsPessoaParaDeletar.add(pessoa6.getId());

		for (int i = 1; i < 6; i++) {
			Emprestimo emprestimoCadastrado1 = service.novoEmprestimo(pessoa1.getId(), livro.getId());
			idsParaDeletar.add(emprestimoCadastrado1.getId());
		}
		for (int i = 1; i < 5; i++) {
			Emprestimo emprestimoCadastrado2 = service.novoEmprestimo(pessoa2.getId(), livro.getId());
			idsParaDeletar.add(emprestimoCadastrado2.getId());
		}
		for (int i = 1; i < 4; i++) {
			Emprestimo emprestimoCadastrado3 = service.novoEmprestimo(pessoa3.getId(), livro.getId());
			idsParaDeletar.add(emprestimoCadastrado3.getId());
		}
		for (int i = 1; i < 3; i++) {
			Emprestimo emprestimoCadastrado4 = service.novoEmprestimo(pessoa4.getId(), livro.getId());
			idsParaDeletar.add(emprestimoCadastrado4.getId());
		}
		for (int i = 1; i < 2; i++) {
			Emprestimo emprestimoCadastrado5 = service.novoEmprestimo(pessoa5.getId(), livro.getId());
			idsParaDeletar.add(emprestimoCadastrado5.getId());
		}
		List<ScorePessoa> pessoas = service.rankingEmprestimos(4L);

		assertEquals(pessoa1.getId(), pessoas.get(0).getPessoa().getId());
		assertEquals(pessoa2.getId(), pessoas.get(1).getPessoa().getId());
		assertEquals(pessoa3.getId(), pessoas.get(2).getPessoa().getId());
		assertEquals(pessoa4.getId(), pessoas.get(3).getPessoa().getId());
		cleanDatabase();
	}

	@Test
	void listRankingPessoasQueMaisAtrasamADevolucao() {
		Livro livro = cadastraLivro();
		idsLivroParaDeletar.add(livro.getId());
		Pessoa pessoa1 = pessoaService.cadastra(new Pessoa("Pessoa1"));
		idsPessoaParaDeletar.add(pessoa1.getId());
		Pessoa pessoa2 = pessoaService.cadastra(new Pessoa("Pessoa2"));
		idsPessoaParaDeletar.add(pessoa2.getId());
		Pessoa pessoa3 = pessoaService.cadastra(new Pessoa("Pessoa3"));
		idsPessoaParaDeletar.add(pessoa3.getId());
		Pessoa pessoa4 = pessoaService.cadastra(new Pessoa("Pessoa4"));
		idsPessoaParaDeletar.add(pessoa4.getId());
		Pessoa pessoa5 = pessoaService.cadastra(new Pessoa("Pessoa5"));
		idsPessoaParaDeletar.add(pessoa5.getId());
		Pessoa pessoa6 = pessoaService.cadastra(new Pessoa("Pessoa6"));
		idsPessoaParaDeletar.add(pessoa6.getId());

		Emprestimo emprestimoCadastrado1 = service.novoEmprestimo(pessoa1.getId(), livro.getId());
		idsParaDeletar.add(emprestimoCadastrado1.getId());
		emprestimoCadastrado1.setDataEmprestimo(emprestimoCadastrado1.getDataEmprestimo().minus(Period.ofDays(10)));
		emprestimoRepository.save(emprestimoCadastrado1);

		Emprestimo emprestimoCadastrado2 = service.novoEmprestimo(pessoa2.getId(), livro.getId());
		idsParaDeletar.add(emprestimoCadastrado2.getId());
		emprestimoCadastrado2.setDataEmprestimo(emprestimoCadastrado2.getDataEmprestimo().minus(Period.ofDays(20)));
		emprestimoRepository.save(emprestimoCadastrado2);

		Emprestimo emprestimoCadastrado3 = service.novoEmprestimo(pessoa3.getId(), livro.getId());
		idsParaDeletar.add(emprestimoCadastrado3.getId());
		emprestimoCadastrado3.setDataEmprestimo(emprestimoCadastrado3.getDataEmprestimo().minus(Period.ofDays(10)));
		emprestimoCadastrado3.setDataRenovacao(emprestimoCadastrado3.getDataEmprestimo().plus(Period.ofDays(6)));
		emprestimoRepository.save(emprestimoCadastrado3);

		Emprestimo emprestimoCadastrado4 = service.novoEmprestimo(pessoa4.getId(), livro.getId());
		idsParaDeletar.add(emprestimoCadastrado4.getId());
		emprestimoCadastrado4.setDataEmprestimo(emprestimoCadastrado4.getDataEmprestimo().minus(Period.ofDays(15)));
		emprestimoCadastrado4.setDataRenovacao(emprestimoCadastrado4.getDataEmprestimo().plus(Period.ofDays(11)));
		emprestimoRepository.save(emprestimoCadastrado4);

		Emprestimo emprestimoCadastrado5 = service.novoEmprestimo(pessoa5.getId(), livro.getId());
		idsParaDeletar.add(emprestimoCadastrado5.getId());

		List<ScorePessoa> pessoas = service.rankingAtrasos();

		assertEquals(pessoa1.getId(), pessoas.get(1).getPessoa().getId());
		assertEquals(pessoa2.getId(), pessoas.get(0).getPessoa().getId());
		assertEquals(pessoa3.getId(), pessoas.get(2).getPessoa().getId());
		assertEquals(pessoa4.getId(), pessoas.get(3).getPessoa().getId());
	}

	private Livro cadastraLivro() {
		return livroService.cadastra(new Livro(TITULO_DO_LIVRO_1, AUTOR_DO_LIVRO_1, 100));
	}

	private Pessoa cadastraPessoa() {
		return pessoaService.cadastra(new Pessoa(NOME_DA_PESSOA_1));
	}

}
