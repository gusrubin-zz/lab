package com.gusrubin.lab.hexagonal.application.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gusrubin.lab.hexagonal.domain.estado.ConsultaCapitalUseCase;

/**
 * @author Gustavo Rubin
 */

@RestController
@RequestMapping("/api/capitais")
public class EstadoController {

	private final ConsultaCapitalUseCase consultaCapital;

	@Autowired
	public EstadoController(ConsultaCapitalUseCase consultaCapitalUseCase) {
		this.consultaCapital = consultaCapitalUseCase;
	}

	@GetMapping("/{uf}")
	public String getCapitalByUf(@PathVariable("uf") String uf) {

		return consultaCapital.consultaCapitalPorUf(uf);
	}

}
