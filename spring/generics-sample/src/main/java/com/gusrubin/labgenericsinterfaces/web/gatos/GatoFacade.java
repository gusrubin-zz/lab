package com.gusrubin.labgenericsinterfaces.web.gatos;

import org.springframework.stereotype.Component;

import com.gusrubin.labgenericsinterfaces.domain.gatos.GatoService;
import com.gusrubin.labgenericsinterfaces.web.animal.AbstractAnimalFacade;

@Component
public class GatoFacade extends AbstractAnimalFacade {

	public GatoFacade(GatoMapper animalMapper, GatoService animalService) {
		super(animalMapper, animalService);
	}

}
