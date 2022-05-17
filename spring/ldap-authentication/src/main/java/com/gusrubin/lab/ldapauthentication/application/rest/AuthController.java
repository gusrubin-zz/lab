package com.gusrubin.lab.ldapauthentication.application.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gusrubin.lab.ldapauthentication.domain.AuthUseCase;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("auth")
public class AuthController {

	private final AuthUseCase auth;

	@Autowired
	public AuthController(AuthUseCase authUseCase) {
		this.auth = authUseCase;
	}

	@PostMapping("/ldaptemplate")
	public ResponseEntity<String> authWithLdapTemplate(@RequestBody AuthenticationDto requestDto) {
		if (!StringUtils.hasText(requestDto.getUsername()) || !StringUtils.hasText(requestDto.getPassword())) {
			throw new IllegalArgumentException("Invalid username or password");
		}
		ResponseEntity<String> response = null;
		if (this.auth.authenticate(requestDto.getUsername(), requestDto.getPassword())) {
			response = ResponseEntity.ok("Authenticated");
		} else {
			response = ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		return response;
	}

	@GetMapping("/spring-security")
	public ResponseEntity<String> authWithSpringSecurity() {
		log.debug("called /auth/login");
		String accessToken = "bla";
		return ResponseEntity.ok(accessToken);
	}

	@GetMapping("/example")
	public ResponseEntity<String> example() {
		log.debug("called /auth/example");
		String accessToken = "example";
		return ResponseEntity.ok(accessToken);
	}

}
