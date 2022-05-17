package com.gusrubin.lab.springboot.jpa.jpachilds.infrastructure.person;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gusrubin.lab.springboot.jpa.jpachilds.domain.person.Person;
import com.gusrubin.lab.springboot.jpa.jpachilds.domain.person.PersonRepositoryPort;

@Component
public class PersonRepositoryAdapter implements PersonRepositoryPort {

	private final PersonRepository personRepository;

	private final ModelMapper mapper;

	@Autowired
	public PersonRepositoryAdapter(PersonRepository personRepository, ModelMapper modelMapper) {
		this.personRepository = personRepository;
		this.mapper = modelMapper;
	}

	@Override
	public List<Person> findAll() {
		return this.personRepository.findAll().stream().map(person -> this.mapper.map(person, Person.class)).toList();
	}

	@Override
	public Person findById(Long id) {
		PersonEntity persistedEntity = this.personRepository.findById(id).orElseThrow();
		return this.mapper.map(persistedEntity, Person.class);
	}

	@Override
	public Person create(Person person) {
		PersonEntity persistedEntity = this.personRepository.save(this.mapper.map(person, PersonEntity.class));
		return this.mapper.map(persistedEntity, Person.class);
	}

	@Override
	public Person update(Long id, Person person) {
		if (this.personRepository.findById(id).isEmpty()) {
			throw new IllegalStateException("Trying to update no registered entity");
		}
		person.setId(id);
		return this.mapper.map(this.personRepository.save(this.mapper.map(person, PersonEntity.class)), Person.class);
	}

	@Override
	public void delete(Long id) {
		PersonEntity persistedEnity = this.personRepository.findById(id).orElseThrow();
		this.personRepository.delete(persistedEnity);
	}

}
