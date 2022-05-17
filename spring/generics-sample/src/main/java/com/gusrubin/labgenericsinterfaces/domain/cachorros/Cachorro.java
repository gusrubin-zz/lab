package com.gusrubin.labgenericsinterfaces.domain.cachorros;

import java.time.LocalDateTime;

import com.gusrubin.labgenericsinterfaces.domain.animal.Animal;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class Cachorro extends Animal {
	
	String dateTime;
	String latido;
	
	@Builder(builderMethodName = "cachorroBuilder")
	public Cachorro(String cor, String latido) {
		super("cachorro", cor);
		this.dateTime = LocalDateTime.now().toString();
		this.latido = latido;
	}
	

}
