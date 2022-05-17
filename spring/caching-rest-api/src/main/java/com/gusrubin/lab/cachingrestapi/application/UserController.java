package com.gusrubin.lab.cachingrestapi.application;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gusrubin.lab.cachingrestapi.domain.user.User;
import com.gusrubin.lab.cachingrestapi.domain.user.UserUseCase;

@RestController
@RequestMapping("/api/users")
public class UserController {

	private final UserUseCase user;

	private final ModelMapper mapper;

	@Autowired
	public UserController(UserUseCase userUseCase, ModelMapper modelMapper) {
		this.user = userUseCase;
		this.mapper = modelMapper;
	}

	@GetMapping
	public ResponseEntity<List<UserDto>> getAllUsers() {
		List<UserDto> response = user.findAll().stream().map(u -> mapper.map(u, UserDto.class))
				.collect(Collectors.toList());

		return ResponseEntity.ok(response);
	}

	@GetMapping(path = "{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long id) {
		UserDto response = mapper.map(user.findById(id), UserDto.class);

		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<UserDto> postUser(@RequestBody UserDto userDto) {
		User requestUser = mapper.map(userDto, User.class);

		UserDto response = null;

		response = mapper.map(user.create(requestUser, userDto.getPassword()), UserDto.class);

		return ResponseEntity.ok(response);
	}

	@DeleteMapping(path = "{id}")
	public ResponseEntity<Void> deleteUserById(@PathVariable("id") Long id) {
		user.deleteById(id);

		return ResponseEntity.noContent().build();
	}

}
