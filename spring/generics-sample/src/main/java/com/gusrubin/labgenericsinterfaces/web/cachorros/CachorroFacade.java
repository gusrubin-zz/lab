package com.gusrubin.labgenericsinterfaces.web.cachorros;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gusrubin.labgenericsinterfaces.domain.cachorros.CachorroService;
import com.gusrubin.labgenericsinterfaces.web.animal.AbstractAnimalFacade;

@Component
public class CachorroFacade extends AbstractAnimalFacade {

	@Autowired
	public CachorroFacade(CachorroMapper animalMapper, CachorroService animalService) {
		super(animalMapper, animalService);
	}

}
