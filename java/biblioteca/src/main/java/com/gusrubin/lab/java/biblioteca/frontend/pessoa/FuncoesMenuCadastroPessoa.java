package com.gusrubin.lab.java.biblioteca.frontend.pessoa;

import java.util.List;

import com.gusrubin.lab.java.biblioteca.backend.repository.pessoa.Pessoa;
import com.gusrubin.lab.java.biblioteca.backend.service.pessoa.PessoaService;
import com.gusrubin.lab.java.biblioteca.frontend.commons.FuncoesMenusComuns;

/**
 * Funções de Menus de Cadastro de Pessoas
 */
public class FuncoesMenuCadastroPessoa {

	private final FuncoesMenusComuns menusComuns;

	private final PessoaService pessoaService;

	public FuncoesMenuCadastroPessoa(FuncoesMenusComuns menusComuns, PessoaService pessoaService) {
		this.menusComuns = menusComuns;
		this.pessoaService = pessoaService;
	}

	/**
	 * Cadastrar Nova Pessoa
	 * 
	 * @return void
	 */
	public void cadastrarNovaPessoa() {
		try {
			menusComuns.println("\n=> Cadastrar Nova Pessoa <=");
			menusComuns.print("\n[Nome da Pessoa]: ");
			String nome = menusComuns.readString();

			pessoaService.cadastra(new Pessoa(nome));
		} catch (Exception ex) {
			menusComuns.println(ex.getMessage());
		}
	}

	/**
	 * Listar Todas as Pessoas
	 * 
	 * @return void
	 */
	public void listarTodasAsPessoas() {
		try {
			pessoaService.listaTodas().stream().forEach(p -> menusComuns.println(p.toString()));
		} catch (Exception ex) {
			menusComuns.println(ex.getMessage());
		}
	}

	/**
	 * Consultar Pessoas por Nome
	 * 
	 * @return void
	 */
	public void consultarPessoasPorNome() {
		try {
			menusComuns.println("\n=> Consultar Pessoas por Nome <=");
			menusComuns.print("\n[Nome da Pessoa]: ");
			String nome = menusComuns.readString();

			menusComuns.println(pessoaService.buscaPorNome(nome).toString());
		} catch (Exception ex) {
			menusComuns.println(ex.getMessage());
		}
	}

	/**
	 * Editar Cadastro de Pessoa
	 * 
	 * @return void
	 */
	public void editarCadastroDePessoa() {
		try {
			menusComuns.println("\n=> Editar Cadastro de Pessoa <=");
			menusComuns.print("\n[Nome da Pessoa]: ");
			String nome = menusComuns.readString();

			List<Pessoa> pessoas = pessoaService.buscaPorNome(nome);
			pessoas.stream().forEach(l -> menusComuns.println(l.showId()));

			menusComuns.print("\n[Informe o id da pessoa que deseja editar]: ");
			Integer id = menusComuns.readInteger();
			Pessoa pessoa = pessoaService.buscaPorId(id);
			menusComuns.print("\n[Novo Nome da Pessoa (digite 'm' para manter o valor atual)]: ");
			String novoNome = menusComuns.readString();

			pessoa.setNome(novoNome.equals("m") ? pessoa.getNome() : novoNome);

			pessoaService.atualiza(pessoa);
		} catch (Exception ex) {
			menusComuns.println(ex.getMessage());
		}
	}

	/**
	 * Excluir Cadastro de Pessoa
	 * 
	 * @return void
	 */
	public void excluirCadastroDePessoa() {
		try {
			menusComuns.println("\n=> Excluir Cadastro de Pessoa <=");
			menusComuns.print("\n[Nome da Pessoa]: ");
			String nome = menusComuns.readString();

			List<Pessoa> pessoas = pessoaService.buscaPorNome(nome);
			pessoas.stream().forEach(l -> menusComuns.println(l.showId()));

			menusComuns.print("\n[Para confirmar a exclusão, informe o id da pessoa a ser excluída]: ");
			Integer id = menusComuns.readInteger();

			pessoaService.exclui(id);
		} catch (Exception ex) {
			menusComuns.println(ex.getMessage());
		}
	}

}
