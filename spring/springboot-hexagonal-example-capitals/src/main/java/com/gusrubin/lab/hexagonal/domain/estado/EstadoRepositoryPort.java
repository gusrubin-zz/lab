package com.gusrubin.lab.hexagonal.domain.estado;

/**
 * @author Gustavo Rubin
 */

public interface EstadoRepositoryPort {

	Estado findByUf(String uf);

}
