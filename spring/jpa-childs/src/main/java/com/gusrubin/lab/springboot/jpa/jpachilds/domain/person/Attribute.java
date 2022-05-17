package com.gusrubin.lab.springboot.jpa.jpachilds.domain.person;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Attribute {

	private Long id;
	private String name;
	private String value;

}
