package com.gusrubin.lab.java.biblioteca.backend.repository.pessoa;

import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.reflect.TypeToken;
import com.gusrubin.lab.java.biblioteca.backend.repository.commons.datarepository.BaseDataRepository;
import com.gusrubin.lab.java.biblioteca.backend.repository.commons.datarepository.DataModel;

import jline.internal.Nullable;

public class PessoaRepository extends BaseDataRepository<Pessoa> {
	
	public PessoaRepository() {
		super(null);
	}
	
	public PessoaRepository(@Nullable Boolean testDatabase) {
		super(testDatabase);
	}

	@Override
	protected Type getTypeToken() {
		return new TypeToken<DataModel<Pessoa>>() {
		}.getType();
	}

	public List<Pessoa> findByNome(String nome) {
		return findAll().stream().filter(r -> r.getNome().toUpperCase().contains(nome.toUpperCase()))
				.collect(Collectors.toList());
	}

}
