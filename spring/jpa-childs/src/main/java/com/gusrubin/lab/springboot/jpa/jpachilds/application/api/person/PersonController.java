package com.gusrubin.lab.springboot.jpa.jpachilds.application.api.person;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gusrubin.lab.springboot.jpa.jpachilds.domain.person.Person;
import com.gusrubin.lab.springboot.jpa.jpachilds.domain.person.PersonUseCase;

@RestController
@RequestMapping("/api/person")
public class PersonController {

	private final PersonUseCase personUseCase;

	@Autowired
	public PersonController(PersonUseCase personUseCase) {
		this.personUseCase = personUseCase;
	}

	@GetMapping
	public ResponseEntity<List<Person>> getAll() {
		return ResponseEntity.ok(personUseCase.findAll());
	}

	@GetMapping("{personId}")
	public ResponseEntity<Person> getById(@PathVariable("personId") Long personId) {
		Person person = personUseCase.findById(personId);
		return ResponseEntity.ok(person);
	}

	@PostMapping
	public ResponseEntity<Person> post(@RequestBody Person requestBody) {
		return ResponseEntity.ok(personUseCase.create(requestBody));
	}

	@PutMapping("{personId}")
	public ResponseEntity<Person> putById(@PathVariable("personId") Long personId, @RequestBody Person requestBody) {
		return ResponseEntity.ok(personUseCase.update(personId, requestBody));
	}

	@DeleteMapping("{personId}")
	public ResponseEntity<Void> deleteById(@PathVariable("personId") Long personId) {
		personUseCase.delete(personId);
		return ResponseEntity.noContent().build();
	}

}
