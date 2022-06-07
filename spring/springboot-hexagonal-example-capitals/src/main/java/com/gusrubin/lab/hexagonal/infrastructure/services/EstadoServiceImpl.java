package com.gusrubin.lab.hexagonal.infrastructure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gusrubin.lab.hexagonal.domain.estado.EstadoRepositoryPort;
import com.gusrubin.lab.hexagonal.domain.estado.EstadoService;

/**
 * @author Gustavo Rubin
 */

@Service
public class EstadoServiceImpl extends EstadoService {

	@Autowired
	public EstadoServiceImpl(EstadoRepositoryPort estadoRepositoryPort) {
		super(estadoRepositoryPort);
	}

}
