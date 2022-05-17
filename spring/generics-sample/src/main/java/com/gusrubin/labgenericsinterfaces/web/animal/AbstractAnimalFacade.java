package com.gusrubin.labgenericsinterfaces.web.animal;

import org.springframework.beans.factory.annotation.Autowired;

import com.gusrubin.labgenericsinterfaces.domain.animal.AnimalService;

public abstract class AbstractAnimalFacade implements AnimalFacade {

	protected final AnimalMapper<?> animalMapper;
	protected final AnimalService animalService; 

	@Autowired
	public AbstractAnimalFacade(AnimalMapper<?> animalMapper, AnimalService animalService) {
		this.animalMapper = animalMapper;
		this.animalService = animalService;
	}

	@Override
	public AnimalResponseDto create(AnimalRequestDto animalRequestDto) {
		return animalMapper.animalToAnimalResponseDto(
				animalService.create(animalMapper.animalRequestDtoToAnimal(animalRequestDto)));
	}

}
