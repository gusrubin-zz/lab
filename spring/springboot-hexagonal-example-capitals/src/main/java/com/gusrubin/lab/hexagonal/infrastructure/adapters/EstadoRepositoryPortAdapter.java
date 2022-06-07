package com.gusrubin.lab.hexagonal.infrastructure.adapters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gusrubin.lab.hexagonal.domain.estado.Estado;
import com.gusrubin.lab.hexagonal.domain.estado.EstadoRepositoryPort;
import com.gusrubin.lab.hexagonal.infrastructure.database.entities.EstadoEntity;
import com.gusrubin.lab.hexagonal.infrastructure.database.repositories.EstadoRepository;

/**
 * @author Gustavo Rubin
 */

@Component
public class EstadoRepositoryPortAdapter implements EstadoRepositoryPort {

	private final EstadoRepository repository;
	private final ModelMapper mapper;

	@Autowired
	public EstadoRepositoryPortAdapter(EstadoRepository repository, ModelMapper modelMapper) {
		this.repository = repository;
		this.mapper = modelMapper;
	}

	@Override
	public Estado findByUf(String uf) {
		Estado estado = null;
		EstadoEntity entity = repository.findByUf(uf);
		if (entity != null) {
			estado = mapper.map(entity, Estado.class);
		}
		return estado;
	}

}
