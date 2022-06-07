package com.gusrubin.lab.hexagonal.application.schedule;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gusrubin.lab.hexagonal.domain.estado.ConsultaCapitalUseCase;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Gustavo Rubin
 */

@Slf4j
@Component
public class EstadoSchedule {

	private final ConsultaCapitalUseCase consultaCapital;
	private Random random;

	@Autowired
	public EstadoSchedule(ConsultaCapitalUseCase customerCrudUseCase) {
		this.consultaCapital = customerCrudUseCase;
		try {
			this.random = SecureRandom.getInstanceStrong();
		} catch (NoSuchAlgorithmException e) {
			log.error("failed to generate Random instance");
		}
	}

	@Scheduled(fixedRate = 5000)
	public void listCustomers() {
		String[] ufs = { "MA", "PA", "TO", "RS", "MT", "RJ", "SP", "RN", "MG", "BA" };

		int index = random.nextInt(ufs.length);

		log.info("Consulta periódica de capitais: {} - {}", ufs[index],
				consultaCapital.consultaCapitalPorUf(ufs[index]));
	}

}
