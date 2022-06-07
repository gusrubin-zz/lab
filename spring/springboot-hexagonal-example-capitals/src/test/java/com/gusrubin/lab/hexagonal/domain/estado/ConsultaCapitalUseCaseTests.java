package com.gusrubin.lab.hexagonal.domain.estado;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author Gustavo Rubin
 */

@ExtendWith(MockitoExtension.class)
class ConsultaCapitalUseCaseTests {

	@Mock
	private EstadoRepositoryPort estadoRepository;

	@Spy
	@InjectMocks
	private EstadoService service;

	@Test
	void consultaCapitalPorUfSuccessfully() {
		// Preconditions
		Estado estado = EstadoBuilder.buildDefault();
		Mockito.when(estadoRepository.findByUf(Mockito.any())).thenReturn(estado);

		// Test
		String capital = service.consultaCapitalPorUf(estado.getCapital());

		// Test and validations
		assertEquals(estado.getCapital(), capital);
		Mockito.verify(service, times(1)).consultaCapitalPorUf(Mockito.any());
	}

	@Test
	void shouldFailWhenConsultaCapitalPorUfWithoutUf() {
		// Preconditions

		// Test and validations
		assertThrows(IllegalArgumentException.class, () -> service.consultaCapitalPorUf(null));
		Mockito.verify(service, times(1)).consultaCapitalPorUf(Mockito.any());
	}

}
