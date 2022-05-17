package com.gusrubin.lab.springboot.jpa.jpachilds.domain.person;

import java.util.List;

public interface PersonRepositoryPort {
	
	List<Person> findAll();
	Person findById(Long id);
	Person create(Person person);
	Person update(Long id, Person person);
	void delete(Long id);

}
