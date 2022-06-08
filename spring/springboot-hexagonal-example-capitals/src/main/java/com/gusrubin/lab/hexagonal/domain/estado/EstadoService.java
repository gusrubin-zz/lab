package com.gusrubin.lab.hexagonal.domain.estado;

import org.springframework.util.Assert;

/**
 * @author Gustavo Rubin
 */

public class EstadoService implements ConsultaCapitalUseCase {

	private final EstadoRepositoryPort estadoRepository;

	public EstadoService(EstadoRepositoryPort ufRepositoryPort) {
		this.estadoRepository = ufRepositoryPort;
	}

	@Override
	public String consultaCapitalPorUf(String uf) {
		// Validations
		Assert.hasText(uf, "UF é requerida");

		// Performs method goals
		Estado persistedUf = estadoRepository.findByUf(uf.toUpperCase());
		if (persistedUf == null) {
			throw new IllegalArgumentException("UF não existe");
		}
		return persistedUf.getCapital();
	}

}
