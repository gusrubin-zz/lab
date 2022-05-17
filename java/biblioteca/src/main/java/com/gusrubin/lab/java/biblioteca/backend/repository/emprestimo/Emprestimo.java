package com.gusrubin.lab.java.biblioteca.backend.repository.emprestimo;

import java.time.LocalDate;

import com.gusrubin.lab.java.biblioteca.backend.repository.commons.datarepository.EntityModel;

public class Emprestimo extends EntityModel {

	private Integer idLivro;
	private Integer idPessoa;
	private LocalDate dataEmprestimo;
	private LocalDate dataRenovacao;
	private LocalDate dataDevolucao;

	public Emprestimo(Integer livro, Integer pessoa) {
		super();
		dataEmprestimo = LocalDate.now();
		this.idLivro = livro;
		this.idPessoa = pessoa;
	}

	public Integer getIdLivro() {
		return idLivro;
	}

	public void setLivros(Integer livro) {
		this.idLivro = livro;
	}

	public Integer getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(Integer pessoa) {
		this.idPessoa = pessoa;
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

	public LocalDate getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(LocalDate dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public String showId() {
		return "Emprestimo [id: " + id + ", livro: " + idLivro + ", pessoa: " + idPessoa + ", dataEmprestimo: "
				+ dataEmprestimo + ", dataRenovacao: " + dataRenovacao + ", dataDevolucao: " + dataDevolucao + "]";
	}

}
