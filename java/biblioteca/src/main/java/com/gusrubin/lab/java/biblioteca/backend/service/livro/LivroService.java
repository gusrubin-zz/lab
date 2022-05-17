package com.gusrubin.lab.java.biblioteca.backend.service.livro;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;

import com.gusrubin.lab.java.biblioteca.backend.repository.emprestimo.EmprestimoRepository;
import com.gusrubin.lab.java.biblioteca.backend.repository.livro.Livro;
import com.gusrubin.lab.java.biblioteca.backend.repository.livro.LivroRepository;

public class LivroService {

	private final LivroRepository repository;

	private final EmprestimoRepository emprestimoRepository;

	private final Logger log = Logger.getLogger(this.getClass().getName());

	public LivroService(LivroRepository repository, EmprestimoRepository emprestimoRepository) {
		this.emprestimoRepository = emprestimoRepository;
		this.repository = repository;
	}

	public Livro cadastra(Livro entity) {
		if (StringUtils.isBlank(entity.getTitulo())) {
			throw new IllegalArgumentException("Título não pode ser nulo");
		}
		if (StringUtils.isBlank(entity.getAutor())) {
			throw new IllegalArgumentException("Autor não pode ser nulo");
		}
		entity.setId(null);
		entity = repository.save(entity);
		log.info("Novo cadastro de livro " + entity.showId());
		return entity;
	}

	public List<Livro> listaTodos() {
		return repository.findAll();
	}

	public Livro buscaPorId(Integer id) {
		Optional<Livro> result = repository.findById(id);
		if (result.isEmpty()) {
			throw new IllegalStateException("Não existe livro cadastradado com este id.");
		}
		return result.get();
	}

	public List<Livro> buscaPorTitulo(String titulo) {
		List<Livro> entities = repository.findByTitulo(titulo);
		if (entities.isEmpty()) {
			throw new IllegalStateException("Não existe livro cadastradado com este titulo.");
		}
		return entities;
	}

	public List<Livro> buscaPorAutor(String autor) {
		List<Livro> entities = repository.findByAutor(autor);
		if (entities.isEmpty()) {
			throw new IllegalStateException("Não existe livro cadastradado com este autor.");
		}
		return entities;
	}

	public Livro atualiza(Livro entity) {
		if (entity.getExemplaresEmAcervo() < emprestimoRepository.findEmprestimosAtivosByIdLivro(entity.getId())
				.size()) {
			throw new IllegalStateException(
					"Não é possível atualizar os exemplares disponíveis para um número menor do que o de empréstimos ativos deste livro");
		}
		entity = repository.save(entity);
		log.info("Cadastro de livro atualizado " + entity.showId());
		return entity;
	}

	public void exclui(Integer id) {
		buscaPorId(id);

		if (!emprestimoRepository.findEmprestimosAtivosByIdLivro(id).isEmpty()) {
			throw new IllegalStateException("Não é possível excluir esse título pois ainda há exemplares emprestados.");
		}

		log.info("Cadastro de livro excluído. Id " + id);
		repository.delete(id);
	}

}
