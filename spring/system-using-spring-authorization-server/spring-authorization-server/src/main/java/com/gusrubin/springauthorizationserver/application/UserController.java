package com.gusrubin.springauthorizationserver.application;

import java.util.ArrayList;
import java.util.List;
import javax.websocket.server.PathParam;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gusrubin.springauthorizationserver.domain.user.User;
import com.gusrubin.springauthorizationserver.domain.user.UserUseCase;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserUseCase userUseCase;
    private final ModelMapper mapper;

    @Autowired
    public UserController(UserUseCase userUseCase, ModelMapper modelMapper) {
	this.userUseCase = userUseCase;
	this.mapper = modelMapper;
    }

    @PostMapping
    public UserDto postUser(@RequestBody UserDto requestBody) {
	User user = mapper.map(requestBody, User.class);
	return mapper.map(userUseCase.create(user), UserDto.class);
    }

    @GetMapping
    public List<UserDto> getUser(@Nullable @RequestParam("username") String username) {
	List<UserDto> userList = new ArrayList<>();
	if (StringUtils.hasLength(username)) {
	    userList.add(mapper.map(userUseCase.getByUsername(username), UserDto.class));
	} else {
	    userList = userUseCase.getAll().stream().map(user -> mapper.map(user, UserDto.class)).toList();
	}
	return userList;
    }

    @PutMapping("/{username}")
    public UserDto putUser(@PathParam("username") String username, @RequestBody UserDto requestBody) {
	return null;
    }

    @DeleteMapping("/{username}")
    public void deleteUser(@PathParam("username") String username) {
	userUseCase.deleteByUsername(username);
    }

}
