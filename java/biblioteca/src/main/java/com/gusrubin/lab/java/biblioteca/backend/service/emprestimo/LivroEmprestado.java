package com.gusrubin.lab.java.biblioteca.backend.service.emprestimo;

import java.time.LocalDate;

public class LivroEmprestado {

	private Integer idEmprestimo;

	private String tituloLivro;

	private String nomePessoa;

	private LocalDate dataEmprestimo;

	private LocalDate dataRenovacao;

	public LivroEmprestado(Integer idEmprestimo, String tituloLivro, String nomePessoa, LocalDate dataEmprestimo,
			LocalDate dataRenovacao) {
		this.idEmprestimo = idEmprestimo;
		this.tituloLivro = tituloLivro;
		this.nomePessoa = nomePessoa;
		this.dataEmprestimo = dataEmprestimo;
		this.dataRenovacao = dataRenovacao;
	}

	public LivroEmprestado(Integer idEmprestimo, String tituloLivro, String nomePessoa) {
		this.idEmprestimo = idEmprestimo;
		this.tituloLivro = tituloLivro;
		this.nomePessoa = nomePessoa;
	}

	public Integer getIdEmprestimo() {
		return idEmprestimo;
	}

	public void setIdEmprestimo(Integer idEmprestimo) {
		this.idEmprestimo = idEmprestimo;
	}

	public String getTituloLivro() {
		return tituloLivro;
	}

	public void setTituloLivro(String tituloLivro) {
		this.tituloLivro = tituloLivro;
	}

	public String getNomePessoa() {
		return nomePessoa;
	}

	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}

	public LocalDate getDataEmprestimo() {
		return dataEmprestimo;
	}

	public void setDataEmprestimo(LocalDate dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}

	public LocalDate getDataRenovacao() {
		return dataRenovacao;
	}

	public void setDataRenovacao(LocalDate dataRenovacao) {
		this.dataRenovacao = dataRenovacao;
	}

	@Override
	public String toString() {
		return "LivroEmprestado [idEmprestimo=" + idEmprestimo + ", tituloLivro=" + tituloLivro + ", nomePessoa="
				+ nomePessoa + ", dataEmprestimo=" + dataEmprestimo + ", dataRenovacao=" + dataRenovacao + "]";
	}

	public String resume() {
		return "[id: " + idEmprestimo + ", livro: " + tituloLivro + ", em posse de: " + nomePessoa + "]";
	}

}
