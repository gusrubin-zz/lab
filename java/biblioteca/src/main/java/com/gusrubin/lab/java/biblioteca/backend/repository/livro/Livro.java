package com.gusrubin.lab.java.biblioteca.backend.repository.livro;

import com.gusrubin.lab.java.biblioteca.backend.repository.commons.datarepository.EntityModel;

public class Livro extends EntityModel {

	private String titulo;
	private String autor;
	private int exemplaresEmAcervo;

	public Livro() {
		this.exemplaresEmAcervo = 0;
	}

	public Livro(String titulo, String autor) {
		this.titulo = titulo;
		this.autor = autor;
		this.exemplaresEmAcervo = 0;
	}

	public Livro(String titulo, String autor, Integer exemplaresDisponiveis) {
		this.titulo = titulo;
		this.autor = autor;
		if (exemplaresDisponiveis != null) {
			this.exemplaresEmAcervo = exemplaresDisponiveis;
		}
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}	

	public int getExemplaresEmAcervo() {
		return exemplaresEmAcervo;
	}

	public void setExemplaresEmAcervo(int exemplaresEmAcervo) {
		this.exemplaresEmAcervo = exemplaresEmAcervo;
	}

	@Override
	public String toString() {
		return "Livro [titulo: " + titulo + ", autor: " + autor + ", acervo: " + exemplaresEmAcervo + "]";
	}

	public String showId() {
		return "Livro [id: " + id + ", titulo: " + titulo + ", autor: " + autor + "]";
	}

}
