package com.gusrubin.labgenericsinterfaces.domain.gatos;

import org.springframework.stereotype.Service;

import com.gusrubin.labgenericsinterfaces.domain.animal.Animal;
import com.gusrubin.labgenericsinterfaces.domain.animal.AnimalService;

@Service
public class GatoService implements AnimalService {

	@SuppressWarnings("unchecked")
	@Override
	public <T extends Animal, S extends Animal> T create(S animal) {
		
		Gato gato = (Gato) animal;
		
		return (T) Gato.gatoBuilder().cor(animal.getCor()).miado(gato.miado).build();
	}

}
