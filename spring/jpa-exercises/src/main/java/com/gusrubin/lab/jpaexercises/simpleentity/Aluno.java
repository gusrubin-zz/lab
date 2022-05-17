package com.gusrubin.lab.jpaexercises.simpleentity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@AllArgsConstructor
public class Aluno {
	
	@Id
	private Long id;
	private String matricula;
	private String nome;
	private Integer idade;

}
