package com.gusrubin.labgenericsinterfaces.domain.cachorros;

import org.springframework.stereotype.Service;

import com.gusrubin.labgenericsinterfaces.domain.animal.Animal;
import com.gusrubin.labgenericsinterfaces.domain.animal.AnimalService;

@Service
public class CachorroService implements AnimalService {

	@SuppressWarnings("unchecked")
	@Override
	public <T extends Animal, S extends Animal> T create(S animal) {

		Cachorro cachorro = (Cachorro) animal;

		return (T) Cachorro.cachorroBuilder().cor(cachorro.getCor()).latido(cachorro.getLatido()).build();
	}

}
