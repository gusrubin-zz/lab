package com.gusrubin.labgenericsinterfaces.domain.gatos;

import java.time.LocalDateTime;

import com.gusrubin.labgenericsinterfaces.domain.animal.Animal;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class Gato extends Animal {

	String dateTime;
	String miado;

	@Builder(builderMethodName = "gatoBuilder")
	public Gato(String cor, String miado) {
		super("gato", cor);
		this.dateTime = LocalDateTime.now().toString();
		this.miado = miado;
	}

}
