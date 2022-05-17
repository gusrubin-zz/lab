package com.gusrubin.lab.java.biblioteca.frontend.livro;

import java.util.List;

import com.gusrubin.lab.java.biblioteca.backend.repository.livro.Livro;
import com.gusrubin.lab.java.biblioteca.backend.service.livro.LivroService;
import com.gusrubin.lab.java.biblioteca.frontend.commons.FuncoesMenusComuns;

/**
 * Funções de Menus de Cadastro de Livros
 */
public class FuncoesMenuCadastroLivro {

	private final FuncoesMenusComuns menusComuns;

	private final LivroService livroService;

	public FuncoesMenuCadastroLivro(FuncoesMenusComuns menusComuns, LivroService livroService) {
		this.menusComuns = menusComuns;
		this.livroService = livroService;
	}

	/**
	 * Cadastrar Novo Livro
	 * 
	 * @return void
	 */
	public void cadastrarNovoLivro() {
		try {
			menusComuns.println("\n=> Cadastrar Novo Livro <=");
			menusComuns.print("\n[Nome do Autor]: ");
			String autor = menusComuns.readString();
			menusComuns.print("\n[Título do Livro]: ");
			String titulo = menusComuns.readString();
			menusComuns.print("\n[Número de Exemplares Em Acervo]: ");
			Integer exemplaresEmAcervo = menusComuns.readInteger();

			livroService.cadastra(new Livro(titulo, autor, exemplaresEmAcervo));
		} catch (Exception ex) {
			menusComuns.println(ex.getMessage());
		}
	}

	/**
	 * Listar Todos os Livros
	 * 
	 * @return void
	 */
	public void listarTodosOsLivros() {
		try {
			livroService.listaTodos().stream().forEach(l -> menusComuns.println(l.toString()));
		} catch (Exception ex) {
			menusComuns.println(ex.getMessage());
		}
	}

	/**
	 * Consultar Livros por Autor
	 * 
	 * @return void
	 */
	public void consultarLivrosPorAutor() {
		try {
			menusComuns.println("\n=> Consultar Livros por Autor <=");
			menusComuns.print("\n[Nome do Autor]: ");
			String autor = menusComuns.readString();

			livroService.buscaPorAutor(autor).stream().forEach(l -> menusComuns.println(l.toString()));
		} catch (Exception ex) {
			menusComuns.println(ex.getMessage());
		}
	}

	/**
	 * Consultar Livro por Título
	 * 
	 * @return void
	 */
	public void consultarLivroPorTitulo() {
		try {
			menusComuns.println("\n=> Consultar Livros por Título <=");
			menusComuns.print("\n[Título do Livro]: ");
			String titulo = menusComuns.readString();

			livroService.buscaPorTitulo(titulo).stream().forEach(l -> menusComuns.println(l.toString()));
		} catch (Exception ex) {
			menusComuns.println(ex.getMessage());
		}
	}

	/**
	 * Editar Cadastro de Livro
	 * 
	 * @return void
	 */
	public void editarCadastroDeLivro() {
		try {
			menusComuns.println("\n=> Editar Cadastro de Livro <=");
			menusComuns.print("\n[Título do Livro]: ");
			String titulo = menusComuns.readString();

			List<Livro> livros = livroService.buscaPorTitulo(titulo);
			livros.stream().forEach(l -> menusComuns.println(l.showId()));

			menusComuns.print("\n[Informe o id do livro que deseja editar]: ");
			Integer id = menusComuns.readInteger();
			Livro livro = livroService.buscaPorId(id);
			menusComuns.print("\n[Novo Nome do Autor (digite 'm' para manter o valor atual)]: ");
			String novoAutor = menusComuns.readString();
			menusComuns.print("\n[Novo Título do Livro (digite 'm' para manter o valor atual)]: ");
			String novoTitulo = menusComuns.readString();
			menusComuns.print("\n[Novo Número de Exemplares Em Acervo (digite 'm' para manter o valor atual)]: ");
			String novoExemplaresEmAcervo = menusComuns.readString();

			livro.setAutor(novoAutor.equals("m") ? livro.getAutor() : novoAutor);
			livro.setTitulo(novoTitulo.equals("m") ? livro.getTitulo() : novoTitulo);
			livro.setExemplaresEmAcervo(novoExemplaresEmAcervo.equals("m") ? livro.getExemplaresEmAcervo()
					: Integer.valueOf(novoExemplaresEmAcervo));

			livroService.atualiza(livro);
		} catch (Exception ex) {
			menusComuns.println(ex.getMessage());
		}
	}

	/**
	 * Excluir Cadastro de Livro
	 * 
	 * @return void
	 */
	public void excluirCadastroDeLivro() {
		menusComuns.println("\n=> Excluir Cadastro de Livro <=");
		menusComuns.print("\n[Título do Livro]: ");
		String titulo = menusComuns.readString();

		try {
			List<Livro> livros = livroService.buscaPorTitulo(titulo);
			livros.stream().forEach(l -> menusComuns.println(l.showId()));

			menusComuns.print("\n[Para confirmar a exclusão, informe o id do livro a ser excluído]: ");
			Integer id = menusComuns.readInteger();

			livroService.exclui(id);
		} catch (Exception ex) {
			menusComuns.println(ex.getMessage());
		}
	}

}
