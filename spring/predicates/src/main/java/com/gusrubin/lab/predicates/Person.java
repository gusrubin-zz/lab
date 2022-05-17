package com.gusrubin.lab.predicates;

public class Person {

	private String name;
	private Integer age;
	private String gender;
	private Boolean isAlive;

	public Person() {
	}

	public Person(String name, Integer age, String gender, Boolean isAlive) {
		super();
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.isAlive = isAlive;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Boolean getIsAlive() {
		return isAlive;
	}

	public void setIsAlive(Boolean isAlive) {
		this.isAlive = isAlive;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", gender=" + gender + ", isAlive=" + isAlive + "]";
	}

}
