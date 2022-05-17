package com.gusrubin.labgenericsinterfaces.web.gatos;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.gusrubin.labgenericsinterfaces.domain.gatos.Gato;
import com.gusrubin.labgenericsinterfaces.web.animal.AnimalMapper;
import com.gusrubin.labgenericsinterfaces.web.animal.AnimalRequestDto;
import com.gusrubin.labgenericsinterfaces.web.animal.AnimalResponseDto;

@Component
public class GatoMapper implements AnimalMapper<Gato> {

	@Override
	public Gato animalRequestDtoToAnimal(AnimalRequestDto animalRequestDto) {

		Gson gson = new Gson();

		GatoRequestExtraInfoDto gatoRequestExtraInfoDto = gson
				.fromJson(animalRequestDto.getRequestExtraInfo().toString(), GatoRequestExtraInfoDto.class);

		return Gato.gatoBuilder().cor(animalRequestDto.getCor()).miado(gatoRequestExtraInfoDto.miado).build();
	}

	@Override
	public AnimalResponseDto animalToAnimalResponseDto(Gato gato) {

		GatoResponseExtraInfoDto gatoResponseExtraInfo = GatoResponseExtraInfoDto.builder().dateTime(gato.getDateTime())
				.miado(gato.getMiado()).build();

		return AnimalResponseDto.builder().especie(gato.getEspecie()).cor(gato.getCor())
				.responseExtraInfo(gatoResponseExtraInfo).build();
	}

}
