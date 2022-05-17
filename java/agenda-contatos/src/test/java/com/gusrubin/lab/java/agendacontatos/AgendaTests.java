package com.gusrubin.lab.java.agendacontatos;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.UUID;

import org.junit.jupiter.api.Test;

class AgendaTests {

	private static Contato buildContato(String nome) {
		Contato contato = new Contato();
		contato.setNome(nome);
		contato.setSobrenome("de Tal");
		contato.setEmail("fulano@detal.com");
		contato.setTelefone("1198765432");
		contato.setAniversario(LocalDate.of(1990, Month.JANUARY, 01));
		return contato;
	}

	private static Contato buildContatoFulano() {
		return buildContato("Fulando");
	}

	private static Contato buildContatoSicrano() {
		return buildContato("Sicrano");
	}

	@Test
	void shouldAddContatoToAgenda() {
		Agenda agenda = new Agenda();
		Contato contato = buildContatoFulano();

		assertTrue(agenda.listaTodos().isEmpty());
		agenda.adiciona(contato);
		assertEquals(contato, agenda.buscaPorId(contato.getId()));
	}
	
	@Test
	void shouldFailOnAddContatoWithInvalidNameToAgenda() {
		Agenda agenda = new Agenda();
		Contato contato = buildContatoFulano();
		
		contato.setNome(null);		
		assertThrows(IllegalArgumentException.class, () -> agenda.adiciona(contato));
		
		contato.setNome("");		
		assertThrows(IllegalArgumentException.class, () -> agenda.adiciona(contato));
		
		contato.setNome(" ");		
		assertThrows(IllegalArgumentException.class, () -> agenda.adiciona(contato));
	}
	
	@Test
	void shouldFailOnAddContatoWithInvalidEmailToAgenda() {
		Agenda agenda = new Agenda();
		Contato contato = buildContatoFulano();
		
		contato.setEmail(null);		
		assertThrows(IllegalArgumentException.class, () -> agenda.adiciona(contato));
		
		contato.setEmail("");		
		assertThrows(IllegalArgumentException.class, () -> agenda.adiciona(contato));
		
		contato.setEmail(" ");		
		assertThrows(IllegalArgumentException.class, () -> agenda.adiciona(contato));
		
		contato.setEmail("fulano");		
		assertThrows(IllegalArgumentException.class, () -> agenda.adiciona(contato));
		
		contato.setEmail("fulano.com");		
		assertThrows(IllegalArgumentException.class, () -> agenda.adiciona(contato));
		
		contato.setEmail("fulano@fulano");		
		assertThrows(IllegalArgumentException.class, () -> agenda.adiciona(contato));
	}

	@Test
	void shouldAddTwoContatosToAgenda() {
		Agenda agenda = new Agenda();
		Contato contatoFulano = buildContatoFulano();
		Contato contatoSicrano = buildContatoSicrano();

		assertTrue(agenda.listaTodos().isEmpty());
		agenda.adiciona(contatoFulano);
		agenda.adiciona(contatoSicrano);
		assertEquals(contatoFulano, agenda.buscaPorId(contatoFulano.getId()));
		assertEquals(contatoSicrano, agenda.buscaPorId(contatoSicrano.getId()));
	}

	@Test
	void shouldListAllContatosOfNonEmptyAgenda() {
		Agenda agenda = new Agenda();
		Contato contatoFulano = buildContatoFulano();
		Contato contatoSicrano = buildContatoSicrano();
		agenda.adiciona(contatoFulano);
		agenda.adiciona(contatoSicrano);

		assertEquals(2, agenda.listaTodos().size());
	}

	@Test
	void shouldListAllContatosOfEmptyAgenda() {
		Agenda agenda = new Agenda();

		assertEquals(0, agenda.listaTodos().size());
	}

	@Test
	void shouldListAllContatosPorNomeOfFromAgenda() {
		Agenda agenda = new Agenda();
		Contato contatoFulano = buildContatoFulano();
		Contato contatoSicrano = buildContatoSicrano();
		Contato contatoSicrano2 = buildContatoSicrano();

		assertTrue(agenda.listaTodos().isEmpty());
		agenda.adiciona(contatoFulano);
		agenda.adiciona(contatoSicrano);
		agenda.adiciona(contatoSicrano2);
		assertEquals(2, agenda.buscaPorNome(contatoSicrano.getNome()).size());
	}

	@Test
	void shouldListAllContatosPorNomeOfEmptyAgenda() {
		Agenda agenda = new Agenda();
		Contato contatoFulano = buildContatoFulano();
		String nomeContatoFulano = contatoFulano.getNome();

		assertTrue(agenda.listaTodos().isEmpty());
		assertThrows(IllegalStateException.class, () -> agenda.buscaPorNome(nomeContatoFulano));
	}

	@Test
	void shouldListContatoPorIdOfAgenda() {
		Agenda agenda = new Agenda();
		Contato contatoFulano = buildContatoFulano();
		Contato contatoSicrano = buildContatoSicrano();

		agenda.adiciona(contatoFulano);
		agenda.adiciona(contatoSicrano);
		assertEquals(contatoFulano, agenda.buscaPorId(contatoFulano.getId()));
		assertEquals(contatoSicrano, agenda.buscaPorId(contatoSicrano.getId()));
	}

	@Test
	void shouldListContatoPorIdOfEmptyAgenda() {
		Agenda agenda = new Agenda();
		Contato contatoFulano = buildContatoFulano();
		UUID idContatoFulano = contatoFulano.getId();

		assertThrows(IllegalStateException.class, () -> agenda.buscaPorId(idContatoFulano));
	}

	@Test
	void shouldRemoveOneContatoFromAgenda() {
		Agenda agenda = new Agenda();
		Contato contatoFulano = buildContatoFulano();
		agenda.adiciona(contatoFulano);

		assertFalse(agenda.listaTodos().isEmpty());
		agenda.remove(contatoFulano.getId());
		assertTrue(agenda.listaTodos().isEmpty());
	}

	@Test
	void shouldFailOnRemoveOneContatoFromEmptyAgenda() {
		Agenda agenda = new Agenda();
		Contato contatoFulano = buildContatoFulano();
		UUID idContatoFulano = contatoFulano.getId();

		assertTrue(agenda.listaTodos().isEmpty());
		assertThrows(IllegalStateException.class, () -> agenda.remove(idContatoFulano));
	}

}
