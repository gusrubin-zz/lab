package com.gusrubin.springexampleresourceserver.application;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("api/users")
public class UserRestrictInfoController {

	@GetMapping
	public String getUsers() {
		return "This is a user restrict information from resource server";
	}

}
