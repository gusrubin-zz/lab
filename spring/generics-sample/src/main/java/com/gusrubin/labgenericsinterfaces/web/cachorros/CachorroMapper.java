package com.gusrubin.labgenericsinterfaces.web.cachorros;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.gusrubin.labgenericsinterfaces.domain.cachorros.Cachorro;
import com.gusrubin.labgenericsinterfaces.web.animal.AnimalMapper;
import com.gusrubin.labgenericsinterfaces.web.animal.AnimalRequestDto;
import com.gusrubin.labgenericsinterfaces.web.animal.AnimalResponseDto;

@Component
public class CachorroMapper implements AnimalMapper<Cachorro> {

	@Override
	public Cachorro animalRequestDtoToAnimal(AnimalRequestDto animalRequestDto) {

		Gson gson = new Gson();

		CachorroRequestExtraInfoDto cachorroRequestExtraInfoDto = gson
				.fromJson(animalRequestDto.getRequestExtraInfo().toString(), CachorroRequestExtraInfoDto.class);

		return Cachorro.cachorroBuilder().cor(animalRequestDto.getCor()).latido(cachorroRequestExtraInfoDto.getLatido())
				.build();
	}

	@Override
	public AnimalResponseDto animalToAnimalResponseDto(Cachorro cachorro) {

		CachorroResponseExtraInfoDto cachorroResponseExtraInfo = CachorroResponseExtraInfoDto.builder()
				.dateTime(cachorro.getDateTime()).latido(cachorro.getLatido()).build();

		return AnimalResponseDto.builder().especie(cachorro.getEspecie()).cor(cachorro.getCor())
				.responseExtraInfo(cachorroResponseExtraInfo).build();
	}

}
