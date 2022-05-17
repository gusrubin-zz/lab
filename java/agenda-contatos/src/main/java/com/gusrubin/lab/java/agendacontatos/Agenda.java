package com.gusrubin.lab.java.agendacontatos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

public class Agenda {

	private List<Contato> contatos;

	public Agenda() {
		this.contatos = new ArrayList<>();
	}

	public void adiciona(Contato contato) {
		if (StringUtils.isBlank(contato.getNome())) {
			throw new IllegalArgumentException("O nome não pode ser nulo.");
		}
		if (StringUtils.isBlank(contato.getEmail()) || !StringUtils.containsAny(contato.getEmail(), "@")
				|| !StringUtils.containsAny(contato.getEmail(), ".")) {
			throw new IllegalArgumentException("Deve ser informado um endereço de email válido.");
		}
		this.contatos.add(contato);
		System.out.println("Adicionado contato: id=" + contato.getId() + ", nome=" + contato.getNome());
	}

	private Contato buscaId(UUID id) {
		Optional<Contato> result = this.contatos.stream().filter(c -> c.getId().equals(id)).findFirst();
		return result.isPresent() ? result.get() : null;
	}

	public List<Contato> listaTodos() {
		System.out.println("Listando todos os contatos");
		return this.contatos;
	}

	public List<Contato> buscaPorNome(String nome) {
		System.out.println("Buscando contato por nome=" + nome);
		List<Contato> contatosComONome = this.contatos.stream().filter(c -> c.getNome().equals(nome))
				.collect(Collectors.toList());

		if (contatosComONome.isEmpty()) {
			System.out.println("Nenhum contato cadastrado com o nome=" + nome);
			throw new IllegalStateException("Nenhum contato cadastrado com o nome=" + nome);
		}
		return contatosComONome;
	}

	public Contato buscaPorId(UUID id) {
		System.out.println("Buscando contato por id=" + id);
		Contato contato = buscaId(id);

		if (contato == null) {
			System.out.println("Id de contato não cadastrado");
			throw new IllegalStateException("Id de contato não cadastrado");
		}
		return contato;
	}

	public void remove(UUID id) {
		System.out.println("Removendo contato de id=" + id);
		Contato contato = buscaId(id);

		if (contato == null) {
			System.out.println("Id de contato não cadastrado");
			throw new IllegalStateException("Id de contato não cadastrado");
		}
		this.contatos.remove(contato);
		System.out.println("Removido contato: id=" + contato.getId() + ", nome=" + contato.getNome());
	}

}
