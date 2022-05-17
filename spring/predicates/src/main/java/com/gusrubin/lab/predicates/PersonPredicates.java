package com.gusrubin.lab.predicates;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PersonPredicates {
	
	private PersonPredicates() {};

	public static Predicate<Person> isAdultMale() {
		return p -> p.getAge() > 18 && p.getGender().equalsIgnoreCase("M");
	}

	public static Predicate<Person> isMaleAlive() {
		return p -> p.getGender().equalsIgnoreCase("M") && p.getIsAlive();
	}

	public static List<Person> filterPeople(List<Person> people, Predicate<Person> predicate) {
		return people.stream().filter(predicate).collect(Collectors.<Person>toList());
	}

}
