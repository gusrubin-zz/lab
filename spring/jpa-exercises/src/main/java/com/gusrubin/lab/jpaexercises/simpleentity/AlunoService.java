package com.gusrubin.lab.jpaexercises.simpleentity;

import org.springframework.stereotype.Service;

@Service
public class AlunoService {
	
	private final AlunoRepository repository;
	
	public AlunoService(AlunoRepository repository) {
		this.repository = repository;
	}
	
	public Aluno create(Aluno aluno) {
		return repository.save(aluno);
	}

}
