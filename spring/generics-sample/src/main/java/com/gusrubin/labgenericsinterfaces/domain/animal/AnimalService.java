package com.gusrubin.labgenericsinterfaces.domain.animal;

public interface AnimalService {

	<T extends Animal, S extends Animal> T create(S animal);

}
