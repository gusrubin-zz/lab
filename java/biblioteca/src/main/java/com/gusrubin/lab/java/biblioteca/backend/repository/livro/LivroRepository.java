package com.gusrubin.lab.java.biblioteca.backend.repository.livro;

import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.reflect.TypeToken;
import com.gusrubin.lab.java.biblioteca.backend.repository.commons.datarepository.BaseDataRepository;
import com.gusrubin.lab.java.biblioteca.backend.repository.commons.datarepository.DataModel;

import jline.internal.Nullable;

public class LivroRepository extends BaseDataRepository<Livro> {
	
	public LivroRepository() {
		super(null);
	}
	
	public LivroRepository(@Nullable Boolean testDatabase) {
		super(testDatabase);
	}

	@Override
	protected Type getTypeToken() {
		return new TypeToken<DataModel<Livro>>() {
		}.getType();
	}

	public List<Livro> findByTitulo(String titulo) {
		return findAll().stream().filter(r -> r.getTitulo().toUpperCase().contains(titulo.toUpperCase()))
				.collect(Collectors.toList());
	}

	public List<Livro> findByAutor(String autor) {
		return findAll().stream().filter(r -> r.getAutor().toUpperCase().contains(autor.toUpperCase()))
				.collect(Collectors.toList());
	}

}
