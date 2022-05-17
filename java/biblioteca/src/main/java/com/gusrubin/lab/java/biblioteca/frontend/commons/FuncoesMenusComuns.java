package com.gusrubin.lab.java.biblioteca.frontend.commons;

import java.util.List;

import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;

public class FuncoesMenusComuns {

	private final TextIO textIO;

	public FuncoesMenusComuns() {
		this.textIO = TextIoFactory.getTextIO();
	}

	private void mostraBanner() {
		textIO.getTextTerminal().println("\n|| Aplicativo da Biblioteca ||\n");
	}

	private void mostraOpcaoVoltarMenuAnterior(Short opcao) {
		textIO.getTextTerminal().println(opcao + " - Voltar para o Menu Anterior");
	}

	private void mostraPerguntaOpcao() {
		textIO.getTextTerminal().print("\n[Digite uma Opção]: ");
	}
	
	public void mostraOcaoInvalida() {
		textIO.getTextTerminal().println("Opção Inválida\n");
	}

	public Short geraMenu(String tituloDoMenu, List<String> opcoesDoMenu, Boolean opcaoVoltar) {
		mostraBanner();
		textIO.getTextTerminal().println("=> Menu " + tituloDoMenu + " <=");
		for (int i = 0; i < opcoesDoMenu.size(); i++) {
			textIO.getTextTerminal().println(i + 1 + " - " + opcoesDoMenu.get(i));
		}
		if (opcaoVoltar != null && Boolean.TRUE.equals(opcaoVoltar)) {
			mostraOpcaoVoltarMenuAnterior((short) (opcoesDoMenu.size() + 1));
		}
		mostraPerguntaOpcao();
		return textIO.newShortInputReader().read();
	}
	
	public void print(String text) {
		textIO.getTextTerminal().print(text);
	}
	
	public void println(String text) {
		textIO.getTextTerminal().println(text);
	}
	
	public Short readOption() {
		return textIO.newShortInputReader().read();
	}
	
	public Integer readInteger() {
		return textIO.newIntInputReader().read();
	}
	
	public String readString() {
		return textIO.newStringInputReader().read();
	}

}
