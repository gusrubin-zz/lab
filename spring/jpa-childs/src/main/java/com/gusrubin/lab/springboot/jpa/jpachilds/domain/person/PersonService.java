package com.gusrubin.lab.springboot.jpa.jpachilds.domain.person;

import java.util.List;

public class PersonService implements PersonUseCase {

	private final PersonRepositoryPort personRepositoryPort;

	public PersonService(PersonRepositoryPort personRepositoryPort) {
		this.personRepositoryPort = personRepositoryPort;
	}

	@Override
	public List<Person> findAll() {
		return this.personRepositoryPort.findAll();
	}

	@Override
	public Person findById(Long id) {
		return this.personRepositoryPort.findById(id);
	}

	@Override
	public Person create(Person person) {
		return this.personRepositoryPort.create(person);
	}

	@Override
	public Person update(Long id, Person person) {
		return this.personRepositoryPort.update(id, person);
	}

	@Override
	public void delete(Long id) {
		this.personRepositoryPort.delete(id);
	}

}
