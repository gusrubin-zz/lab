package com.gusrubin.lab.java.biblioteca.backend.repository.emprestimo;

import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.reflect.TypeToken;
import com.gusrubin.lab.java.biblioteca.backend.repository.commons.datarepository.BaseDataRepository;
import com.gusrubin.lab.java.biblioteca.backend.repository.commons.datarepository.DataModel;

public class EmprestimoRepository extends BaseDataRepository<Emprestimo> {

	public EmprestimoRepository() {
		super(null);
	}
	
	public EmprestimoRepository(Boolean testDatabase) {
		super(testDatabase);
	}

	@Override
	protected Type getTypeToken() {
		return new TypeToken<DataModel<Emprestimo>>() {
		}.getType();
	}

	public List<Emprestimo> findAllEmprestimosAtivos() {
		return findAll().stream().filter(e -> e.getDataDevolucao() == null).collect(Collectors.toList());
	}

	public List<Emprestimo> findAllByIdPessoa(Integer idPessoa) {
		return findAll().stream().filter(e -> e.getIdPessoa().equals(idPessoa)).collect(Collectors.toList());
	}

	public List<Emprestimo> findEmprestimosAtivosByIdPessoa(Integer idPessoa) {
		return findAll().stream().filter(e -> e.getDataDevolucao() == null && e.getIdPessoa().equals(idPessoa))
				.collect(Collectors.toList());
	}

	public List<Emprestimo> findEmprestimosAtivosByIdLivro(Integer idLivro) {
		return findAll().stream().filter(e -> e.getDataDevolucao() == null && e.getIdLivro().equals(idLivro))
				.collect(Collectors.toList());
	}

}
