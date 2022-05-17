package com.gusrubin.labgenericsinterfaces.web.gatos;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gusrubin.labgenericsinterfaces.web.animal.AbstractAnimalController;

@RestController
@RequestMapping("/gatos")
public class GatosAnimalController extends AbstractAnimalController {

	public GatosAnimalController(GatoFacade gatoFacade) {
		super(gatoFacade);
	}

}
