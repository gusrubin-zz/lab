package com.gusrubin.lab.springboot.jpa.jpachilds.domain.person;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {
	
	private Long id;
	private String name;
	private String surname;
	private Integer age;
	private List<Attribute> attributes;

}
