package com.gusrubin.labgenericsinterfaces.web.cachorros;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gusrubin.labgenericsinterfaces.web.animal.AbstractAnimalController;

@RestController
@RequestMapping("/cachorros")
public class CachorrosAnimalController extends AbstractAnimalController {

	public CachorrosAnimalController(CachorroFacade cachorroFacade) {
		super(cachorroFacade);
	}

}
