package com.gusrubin.lab.java.biblioteca.backend.repository.pessoa;

import com.gusrubin.lab.java.biblioteca.backend.repository.commons.datarepository.EntityModel;

public class Pessoa extends EntityModel {

	private String nome;

	public Pessoa() {
		super();
	}

	public Pessoa(String nome) {
		super();
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Pessoa [nome: " + nome + "]";
	}

	public String showId() {
		return "Pessoa [id: " + id + ", nome: " + nome + "]";
	}

	public String showRecordDiasAtraso() {
		return "Pessoa [nome: " + nome + "]";
	}

}
