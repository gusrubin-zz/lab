package com.gusrubin.labgenericsinterfaces.web.animal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AnimalResponseDto {

	String especie;
	String cor;
	Object responseExtraInfo;

}
