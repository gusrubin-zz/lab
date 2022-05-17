package com.gusrubin.lab.java.agendacontatos;

import java.time.LocalDate;
import java.util.UUID;

public class Contato {
	
	private UUID id;
	
	private String nome;
	
	private String sobrenome;
	
	private String email;
	
	private String telefone;

	private LocalDate aniversario;
	
	public Contato() {
		this.id = UUID.randomUUID();
	}
	
	public Contato(String nome, String sobrenome, String email, String telefone, LocalDate aniversario) {
		this.id = UUID.randomUUID();
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.email = email;
		this.telefone = telefone;
		this.aniversario = aniversario;
	}

	public UUID getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public LocalDate getAniversario() {
		return aniversario;
	}

	public void setAniversario(LocalDate aniversario) {
		this.aniversario = aniversario;
	}	

}
