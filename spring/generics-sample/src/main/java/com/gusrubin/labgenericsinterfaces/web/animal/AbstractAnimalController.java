package com.gusrubin.labgenericsinterfaces.web.animal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public abstract class AbstractAnimalController {

	protected final AnimalFacade animalFacade;
	
	public <T extends AnimalFacade> AbstractAnimalController(T animalFacade) {
		this.animalFacade = animalFacade;
	}

	@PostMapping("/animais")
	public ResponseEntity<AnimalResponseDto> createAnimal(@RequestBody AnimalRequestDto requestBody) {

		AnimalResponseDto responseBody = animalFacade.create(requestBody);

		if (responseBody == null) {
			return null;
		} else {
			return ResponseEntity.ok(responseBody);
		}
	}

}
