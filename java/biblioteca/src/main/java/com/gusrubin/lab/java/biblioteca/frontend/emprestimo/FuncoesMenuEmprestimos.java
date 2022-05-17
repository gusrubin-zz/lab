package com.gusrubin.lab.java.biblioteca.frontend.emprestimo;

import com.gusrubin.lab.java.biblioteca.backend.service.emprestimo.EmprestimoService;
import com.gusrubin.lab.java.biblioteca.backend.service.livro.LivroService;
import com.gusrubin.lab.java.biblioteca.backend.service.pessoa.PessoaService;
import com.gusrubin.lab.java.biblioteca.frontend.commons.FuncoesMenusComuns;

/**
 * Funções de Menus de Empréstimos
 */
public class FuncoesMenuEmprestimos {

	private final FuncoesMenusComuns menusComuns;

	private final EmprestimoService emprestimoService;

	private final LivroService livroService;

	private final PessoaService pessoaService;

	public FuncoesMenuEmprestimos(FuncoesMenusComuns menusComuns, EmprestimoService emprestimoService,
			LivroService livroService, PessoaService pessoaService) {
		this.menusComuns = menusComuns;
		this.emprestimoService = emprestimoService;
		this.livroService = livroService;
		this.pessoaService = pessoaService;
	}

	/**
	 * Registrar Novo Empréstimo
	 * 
	 * @return void
	 */
	public void registrarNovoEmprestimo() {
		try {
			menusComuns.println("\n=> Registrar Novo Empréstimo <=");

			menusComuns.print("\n[Título do Livro]: ");
			String titulo = menusComuns.readString();
			livroService.buscaPorTitulo(titulo).stream().forEach(l -> menusComuns.println(l.showId()));
			menusComuns.print("\n[Informe o id do livro que está sendo emprestado]: ");
			Integer idLivro = menusComuns.readInteger();

			menusComuns.print("\n[Nome da Pessoa]: ");
			String nomePessoa = menusComuns.readString();
			pessoaService.buscaPorNome(nomePessoa).stream().forEach(p -> menusComuns.println(p.showId()));
			menusComuns.print("\n[Confirme o id da pessoa para quem o livro será emprestado]: ");
			Integer idPessoa = menusComuns.readInteger();

			Integer codigoEmprestimo = emprestimoService.novoEmprestimo(idPessoa, idLivro).getId();
			menusComuns.print("Gerado registro de empréstimo de código: " + codigoEmprestimo);
		} catch (Exception ex) {
			menusComuns.println(ex.getMessage());
		}
	}

	/**
	 * Registrar Renovação de Empréstimo
	 * 
	 * @return void
	 */
	public void registrarRenovacaoDeEmprestimo() {
		try {
			menusComuns.println("\n=> Registrar a Renovação de Um Empréstimo <=");

			menusComuns.print("\n[Nome da Pessoa]: ");
			String nomePessoa = menusComuns.readString();
			pessoaService.buscaPorNome(nomePessoa).stream().forEach(p -> menusComuns.println(p.showId()));
			menusComuns.print("\n[Confirme o id da pessoa]: ");
			Integer idPessoa = menusComuns.readInteger();

			emprestimoService.livrosEmprestadosPorPessoa(idPessoa).stream()
					.forEach(e -> menusComuns.println(e.resume()));
			menusComuns.print("\n[Informe o id do empréstimo que a pessoa deseja renovar]: ");
			Integer idEmprestimo = menusComuns.readInteger();

			emprestimoService.renovaEmprestimo(idEmprestimo);
		} catch (Exception ex) {
			menusComuns.println(ex.getMessage());
		}
	}

	/**
	 * Listar Todos os Empréstimos
	 * 
	 * @return void
	 */
	public void listarTodosOsEmprestimos() {
		try {
			menusComuns.println("\n=> Listar Todos os Empréstimos <=");

			emprestimoService.livrosEmprestados().stream().forEach(e -> menusComuns.println(e.resume()));
		} catch (Exception ex) {
			menusComuns.println(ex.getMessage());
		}
	}

	/**
	 * Consultar Empréstimos por Pessoa
	 * 
	 * @return void
	 */
	public void consultarEmprestimosPorPessoa() {
		try {
			menusComuns.println("\n=> Consultar Empréstimos por Pessoa <=");

			menusComuns.print("\n[Nome da Pessoa]: ");
			String nomePessoa = menusComuns.readString();
			pessoaService.buscaPorNome(nomePessoa).stream().forEach(p -> menusComuns.println(p.showId()));
			menusComuns.print("\n[Confirme o id da pessoa]: ");
			Integer idPessoa = menusComuns.readInteger();

			emprestimoService.livrosEmprestadosPorPessoa(idPessoa).stream()
					.forEach(e -> menusComuns.println(e.resume()));
		} catch (Exception ex) {
			menusComuns.println(ex.getMessage());
		}
	}

	/**
	 * Consultar Empréstimos por Livro
	 * 
	 * @return void
	 */
	public void consultarEmprestimosPorLivro() {
		try {
			menusComuns.println("\n=> Consultar Empréstimos por Livro <=");

			menusComuns.print("\n[Título do Livro]: ");
			String titulo = menusComuns.readString();
			livroService.buscaPorTitulo(titulo).stream().forEach(l -> menusComuns.println(l.showId()));
			menusComuns.print("\n[Informe o id do livro que está sendo emprestado]: ");
			Integer idLivro = menusComuns.readInteger();

			emprestimoService.livrosEmprestadosPorLivro(idLivro).stream().forEach(e -> menusComuns.println(e.resume()));
		} catch (Exception ex) {
			menusComuns.println(ex.getMessage());
		}
	}

	/**
	 * Registrar Devolução de Livros
	 * 
	 * @return void
	 */
	public void registrarDevolucaoDeLivros() {
		menusComuns.println("\n=> Registrar Devolução de Livros <=");
		Integer idPessoa = null;

		try {
			menusComuns.print("\n[Nome da Pessoa]: ");
			String nomePessoa = menusComuns.readString();
			pessoaService.buscaPorNome(nomePessoa).stream().forEach(p -> menusComuns.println(p.showId()));
			menusComuns.print("\n[Confirme o id da pessoa]: ");
			idPessoa = menusComuns.readInteger();
		} catch (Exception ex) {
			menusComuns.println(ex.getMessage());
		}

		String novaDevolucao;
		Integer totalMultaPorAtraso = 0;
		do {
			try {
				emprestimoService.livrosEmprestadosPorPessoa(idPessoa).stream()
						.forEach(e -> menusComuns.println(e.resume()));

				menusComuns.print("\n[Informe o id do empréstimo que deseja registrar devolução]: ");
				Integer idEmprestimo = menusComuns.readInteger();

				totalMultaPorAtraso += emprestimoService.devolucao(idEmprestimo);
			} catch (Exception ex) {
				menusComuns.println(ex.getMessage());
			}

			if (totalMultaPorAtraso != 0) {
				menusComuns.println("Total de multa por atraso: R$ " + totalMultaPorAtraso.toString());
			}

			menusComuns.print("\n[Registrar mais uma devolução? (s/n)]: ");
			novaDevolucao = menusComuns.readString();

		} while (novaDevolucao.equalsIgnoreCase("s"));
	}

	/**
	 * Gerar Relatório das 10 Pessoas que Mais Emprestam Livros
	 * 
	 * @return void
	 */
	public void gerarRelatorioTop10PessoasQueEmprestamLivros() {
		try {
			menusComuns.println("\n=> Relatório das 10 Pessoas que Mais Emprestam Livros <=");

			emprestimoService.rankingEmprestimos(10L).stream()
					.forEach(p -> menusComuns.println(p.showEmprestimosScore()));
		} catch (Exception ex) {
			menusComuns.println(ex.getMessage());
		}
	}

	/**
	 * Gerar Relatório das Pessoas que Atrasam a Devolução
	 * 
	 * @return void
	 */
	public void gerarRelatorioDasPessoasQueAtrasamADevolucao() {
		try {
			menusComuns.println("\n=> Relatório das Pessoas que Atrasam a Devolução <=");

			emprestimoService.rankingAtrasos().stream().forEach(p -> menusComuns.println(p.showAtrasosScore()));
		} catch (Exception ex) {
			menusComuns.println(ex.getMessage());
		}
	}

}
