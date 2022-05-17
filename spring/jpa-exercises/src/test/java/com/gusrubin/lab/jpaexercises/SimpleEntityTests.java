package com.gusrubin.lab.jpaexercises;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.gusrubin.lab.jpaexercises.simpleentity.Aluno;
import com.gusrubin.lab.jpaexercises.simpleentity.AlunoRepository;
import com.gusrubin.lab.jpaexercises.simpleentity.AlunoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@ActiveProfiles("test")
class SimpleEntityTests {
	
	AlunoRepository repository;
	AlunoService service;

	@Test
	void createAluno() {
		Aluno aluno1 = Aluno.builder().nome("José").idade(15).build();
		
		Aluno createdAluno = service.create(aluno1);
		
		assertEquals(aluno1, createdAluno);
	}

}
