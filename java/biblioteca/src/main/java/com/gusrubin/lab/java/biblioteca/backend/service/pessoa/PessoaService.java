package com.gusrubin.lab.java.biblioteca.backend.service.pessoa;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;

import com.gusrubin.lab.java.biblioteca.backend.repository.emprestimo.EmprestimoRepository;
import com.gusrubin.lab.java.biblioteca.backend.repository.pessoa.Pessoa;
import com.gusrubin.lab.java.biblioteca.backend.repository.pessoa.PessoaRepository;

public class PessoaService {

	private final PessoaRepository repository;

	private final EmprestimoRepository emprestimoRepository;

	private final Logger log = Logger.getLogger(this.getClass().getName());

	public PessoaService(PessoaRepository repository, EmprestimoRepository emprestimoRepository) {
		this.repository = repository;
		this.emprestimoRepository = emprestimoRepository;
	}

	public Pessoa cadastra(Pessoa entity) {
		if (StringUtils.isBlank(entity.getNome())) {
			throw new IllegalArgumentException("O nome não pode ser nulo");
		}
		entity.setId(null);
		entity = repository.save(entity);
		log.info("Novo cadastro de pessoa " + entity.showId());
		return entity;
	}

	public List<Pessoa> listaTodas() {
		return repository.findAll();
	}

	public Pessoa buscaPorId(Integer id) {
		Optional<Pessoa> result = repository.findById(id);
		if (result.isEmpty()) {
			throw new IllegalStateException("Não existe pessoa cadastrada com este id.");
		}
		return result.get();
	}

	public List<Pessoa> buscaPorNome(String nome) {
		List<Pessoa> entities = repository.findByNome(nome);
		if (entities.isEmpty()) {
			throw new IllegalStateException("Não existe nenhuma pessoa não cadastrada com este nome.");
		}
		return entities;
	}

	public Pessoa atualiza(Pessoa entity) {
		Optional<Pessoa> result = repository.findById(entity.getId());
		if (result.isEmpty()) {
			throw new IllegalStateException("Não existe pessoa cadastrada com este id.");
		}
		entity = repository.save(entity);
		log.info("Cadastro de pessoa atualizado " + entity.showId());
		return entity;
	}

	public void exclui(Integer id) {
		Optional<Pessoa> result = repository.findById(id);
		if (result.isEmpty()) {
			throw new IllegalStateException("Não existe pessoa cadastrada com este id.");
		}
		if (!emprestimoRepository.findEmprestimosAtivosByIdPessoa(id).isEmpty()) {
			throw new IllegalStateException(
					"Não é possível excluir a pessoa pois ela ainda está com a posse de livros.");
		}
		log.info("Cadastro de pessoa excluido. Id " + id);
		repository.delete(id);
	}

}
