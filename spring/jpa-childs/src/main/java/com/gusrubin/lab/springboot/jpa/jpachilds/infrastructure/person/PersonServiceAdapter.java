package com.gusrubin.lab.springboot.jpa.jpachilds.infrastructure.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gusrubin.lab.springboot.jpa.jpachilds.domain.person.PersonRepositoryPort;
import com.gusrubin.lab.springboot.jpa.jpachilds.domain.person.PersonService;

@Service
public class PersonServiceAdapter extends PersonService {

	@Autowired
	public PersonServiceAdapter(PersonRepositoryPort personRepositoryPort) {
		super(personRepositoryPort);
	}

}
