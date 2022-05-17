package com.gusrubin.lab.java.biblioteca.backend.service.emprestimo;

import com.gusrubin.lab.java.biblioteca.backend.repository.pessoa.Pessoa;

public class ScorePessoa {

	private Pessoa pessoa;

	private Integer numeroEmprestimos;

	private Integer recordDiasAtraso;

	public ScorePessoa(Pessoa pessoa, Integer numeroEmprestimos, Integer recordDiasAtraso) {
		this.pessoa = pessoa;
		this.numeroEmprestimos = numeroEmprestimos;
		this.recordDiasAtraso = recordDiasAtraso;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Integer getNumeroEmprestimos() {
		return numeroEmprestimos;
	}

	public void setNumeroEmprestimos(Integer numeroEmprestimos) {
		this.numeroEmprestimos = numeroEmprestimos;
	}

	public Integer getRecordDiasAtraso() {
		return recordDiasAtraso;
	}

	public void setRecordDiasAtraso(Integer recordDiasAtraso) {
		this.recordDiasAtraso = recordDiasAtraso;
	}

	public String showEmprestimosScore() {
		return "[" + pessoa.getNome() + " - " + numeroEmprestimos + " empréstimos]";
	}

	public String showAtrasosScore() {
		return "[" + pessoa.getNome() + " - " + recordDiasAtraso + " dias de atraso]";
	}

}
