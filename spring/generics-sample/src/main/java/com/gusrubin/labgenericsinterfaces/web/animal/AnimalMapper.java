package com.gusrubin.labgenericsinterfaces.web.animal;

import com.gusrubin.labgenericsinterfaces.domain.animal.Animal;

public interface AnimalMapper<T extends Animal> {

	T animalRequestDtoToAnimal(AnimalRequestDto animalRequestDto);

	AnimalResponseDto animalToAnimalResponseDto(T animal);

}
