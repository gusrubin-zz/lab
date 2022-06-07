package com.gusrubin.lab.hexagonal.infrastructure.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gusrubin.lab.hexagonal.infrastructure.database.entities.EstadoEntity;

/**
 * @author Gustavo Rubin
 */

@Repository
public interface EstadoRepository extends JpaRepository<EstadoEntity, Long> {

	EstadoEntity findByUf(String uf);

}
