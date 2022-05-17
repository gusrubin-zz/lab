package com.gusrubin.lab.predicates;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import static com.gusrubin.lab.predicates.PersonPredicates.*;

class PersonTests {

	@Test
	void createAPerson() {
		Person person = new Person();
		person.setName("José");
		person.setAge(12);
		person.setGender("M");
		person.setIsAlive(true);
		
		System.out.println(person.toString());
	}
	
	@Test
	void verifySomePersonPredicates() {
		Person person1 = new Person("José", 12, "M", true);
		Person person2 = new Person("Maria", 19, "F", false);
		Person person3 = new Person("Mario", 19, "M", true);
		
		List<Person> people = new ArrayList<>();
		people.add(person1);
		people.add(person2);
		people.add(person3);
		
		System.out.println(filterPeople(people, isMaleAlive()));
		
		System.out.println(filterPeople(people, isAdultMale()));
		
//		System.out.println(filterPeople(people, isMaleAlive()));
	}

}
