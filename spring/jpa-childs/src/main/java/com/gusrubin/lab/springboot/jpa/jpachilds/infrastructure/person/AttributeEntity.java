package com.gusrubin.lab.springboot.jpa.jpachilds.infrastructure.person;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.gusrubin.lab.springboot.jpa.jpachilds.domain.person.Person;

import lombok.Data;

@Data
@Entity(name = "person_attribute")
public class AttributeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String name;
	@Column
	private String value;
//	@ManyToOne(fetch = FetchType.LAZY)
//	private Person person;

}
