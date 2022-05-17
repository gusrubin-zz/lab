package com.gusrubin.lab.java.biblioteca.frontend;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.gusrubin.lab.java.biblioteca.backend.repository.emprestimo.EmprestimoRepository;
import com.gusrubin.lab.java.biblioteca.backend.repository.livro.LivroRepository;
import com.gusrubin.lab.java.biblioteca.backend.repository.pessoa.PessoaRepository;
import com.gusrubin.lab.java.biblioteca.backend.service.emprestimo.EmprestimoService;
import com.gusrubin.lab.java.biblioteca.backend.service.livro.LivroService;
import com.gusrubin.lab.java.biblioteca.backend.service.pessoa.PessoaService;
import com.gusrubin.lab.java.biblioteca.frontend.commons.FuncoesMenusComuns;
import com.gusrubin.lab.java.biblioteca.frontend.emprestimo.FuncoesMenuEmprestimos;
import com.gusrubin.lab.java.biblioteca.frontend.livro.FuncoesMenuCadastroLivro;
import com.gusrubin.lab.java.biblioteca.frontend.pessoa.FuncoesMenuCadastroPessoa;

public class ApplicationConsole {

	private final FuncoesMenusComuns menusComuns;

	private final FuncoesMenuCadastroLivro menuCadastroLivro;

	private final FuncoesMenuCadastroPessoa menuCadastroPessoa;

	private final FuncoesMenuEmprestimos menuEmprestimos;

	private final Logger log = Logger.getLogger(this.getClass().getName());

	public ApplicationConsole() {
		this.menusComuns = new FuncoesMenusComuns();
		LivroRepository livroRepository = new LivroRepository();
		PessoaRepository pessoaRepository = new PessoaRepository();
		EmprestimoRepository emprestimoRepository = new EmprestimoRepository();
		LivroService livroService = new LivroService(livroRepository, emprestimoRepository);
		PessoaService pessoaService = new PessoaService(pessoaRepository, emprestimoRepository);
		this.menuCadastroLivro = new FuncoesMenuCadastroLivro(menusComuns, livroService);
		this.menuCadastroPessoa = new FuncoesMenuCadastroPessoa(menusComuns, pessoaService);
		this.menuEmprestimos = new FuncoesMenuEmprestimos(menusComuns,
				new EmprestimoService(emprestimoRepository, livroService, pessoaService), livroService, pessoaService);
	}

	/**
	 * Mostra Menu Principal
	 */
	public void mostraMenuPrincipal() {
		List<String> opcoesDoMenu = new ArrayList<>();
		opcoesDoMenu.add("Acessar Cadastro de Livros");
		opcoesDoMenu.add("Acessar Cadastro de Pessoas");
		opcoesDoMenu.add("Acessar Cadastro de Empréstimos");
		opcoesDoMenu.add("Sair do Aplicativo");

		Short option = 0;
		do {
			option = menusComuns.geraMenu("Principal", opcoesDoMenu, false);
			switch (option) {
			case 1:
				mostraMenuCadastroLivros();
				break;
			case 2:
				mostraMenuCadastroPessoas();
				break;
			case 3:
				mostraMenuEmprestimos();
				break;
			default:
				menusComuns.mostraOcaoInvalida();
			}
		} while (option != 4);

		log.info("Application finished");
		System.exit(0);
	}

	/**
	 * Mostra Menu Cadastros de Livros
	 */
	public void mostraMenuCadastroLivros() {
		List<String> opcoesDoMenu = new ArrayList<>();
		opcoesDoMenu.add("Cadastrar Novo Livro");
		opcoesDoMenu.add("Listar Todos os Livros");
		opcoesDoMenu.add("Consultar Livros por Autor");
		opcoesDoMenu.add("Consultar Livro por Título");
		opcoesDoMenu.add("Editar Cadastro de Livro");
		opcoesDoMenu.add("Excluir Cadastro de Livro");

		Short option = 0;
		do {
			option = menusComuns.geraMenu("Cadastro de Livros", opcoesDoMenu, true);
			switch (option) {
			case 1:
				menuCadastroLivro.cadastrarNovoLivro();
				break;
			case 2:
				menuCadastroLivro.listarTodosOsLivros();
				break;
			case 3:
				menuCadastroLivro.consultarLivrosPorAutor();
				break;
			case 4:
				menuCadastroLivro.consultarLivroPorTitulo();
				break;
			case 5:
				menuCadastroLivro.editarCadastroDeLivro();
				break;
			case 6:
				menuCadastroLivro.excluirCadastroDeLivro();
				break;
			default:
				menusComuns.mostraOcaoInvalida();
			}
		} while (option != 7);

		mostraMenuPrincipal();
	}

	/**
	 * Mostra Menu Cadastros de Pessoas
	 */
	public void mostraMenuCadastroPessoas() {
		List<String> opcoesDoMenu = new ArrayList<>();
		opcoesDoMenu.add("Cadastrar Nova Pessoa");
		opcoesDoMenu.add("Listar Todas as Pessoas");
		opcoesDoMenu.add("Consultar Pessoa por Nome");
		opcoesDoMenu.add("Editar Cadastro de Pessoa");
		opcoesDoMenu.add("Excluir Cadastro de Pessoa");

		Short option = 0;
		do {
			option = menusComuns.geraMenu("Cadastro de Pessoas", opcoesDoMenu, true);
			switch (option) {
			case 1:
				menuCadastroPessoa.cadastrarNovaPessoa();
				break;
			case 2:
				menuCadastroPessoa.listarTodasAsPessoas();
				break;
			case 3:
				menuCadastroPessoa.consultarPessoasPorNome();
				break;
			case 4:
				menuCadastroPessoa.editarCadastroDePessoa();
				break;
			case 5:
				menuCadastroPessoa.excluirCadastroDePessoa();
				break;
			default:
				menusComuns.mostraOcaoInvalida();
			}
		} while (option != 6);

		mostraMenuPrincipal();
	}

	/**
	 * Mostra Menu Empréstimos.
	 */
	public void mostraMenuEmprestimos() {
		List<String> opcoesDoMenu = new ArrayList<>();
		opcoesDoMenu.add("Registrar Novo Empréstimo");
		opcoesDoMenu.add("Registrar Renovação de Empréstimo");
		opcoesDoMenu.add("Listar Todos os Empréstimos Ativos");
		opcoesDoMenu.add("Consultar Empréstimos por Pessoa");
		opcoesDoMenu.add("Consultar Empréstimos por Livro");
		opcoesDoMenu.add("Registrar Devolução de Livros");
		opcoesDoMenu.add("Gerar Relatório das 10 Pessoas que Mais Emprestam Livros");
		opcoesDoMenu.add("Gerar Relatório das Pessoas que Atrasam a Devolução");

		Short option = 0;
		do {
			option = menusComuns.geraMenu("Empréstimos", opcoesDoMenu, true);
			switch (option) {
			case 1:
				menuEmprestimos.registrarNovoEmprestimo();
				break;
			case 2:
				menuEmprestimos.registrarRenovacaoDeEmprestimo();
				break;
			case 3:
				menuEmprestimos.listarTodosOsEmprestimos();
				break;
			case 4:
				menuEmprestimos.consultarEmprestimosPorPessoa();
				break;
			case 5:
				menuEmprestimos.consultarEmprestimosPorLivro();
				break;
			case 6:
				menuEmprestimos.registrarDevolucaoDeLivros();
				break;
			case 7:
				menuEmprestimos.gerarRelatorioTop10PessoasQueEmprestamLivros();
				break;
			case 8:
				menuEmprestimos.gerarRelatorioDasPessoasQueAtrasamADevolucao();
				break;
			default:
				menusComuns.mostraOcaoInvalida();
			}
		} while (option != 9);

		mostraMenuPrincipal();
	}

}
