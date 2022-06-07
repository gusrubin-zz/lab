package com.gusrubin.lab.hexagonal.application.cli;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import com.gusrubin.lab.hexagonal.domain.estado.ConsultaCapitalUseCase;

/**
 * @author Gustavo Rubin
 */

@ShellComponent
public class EstadoCommands {

	private final ConsultaCapitalUseCase consultaCapital;

	@Autowired
	public EstadoCommands(ConsultaCapitalUseCase consultaCapitalUseCase) {
		this.consultaCapital = consultaCapitalUseCase;
	}

	@ShellMethod(value = "Consulta capital por UF", group = "estados", key = "consulta capital")
	public String consulta(@ShellOption(value = { "-u", "--uf" }, help = "-u 'uf'}") String uf) {

		return consultaCapital.consultaCapitalPorUf(uf);
	}

}
