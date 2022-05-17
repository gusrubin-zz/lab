package com.gusrubin.springauthorizationserver.domain.user;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.Assert;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserService implements UserUseCase, UserDetailsService {

    private final UserRepositoryPort repository;

    public UserService(UserRepositoryPort userRepository) {
	this.repository = userRepository;
    }

    @Override
    public User create(User user) {
	return repository.save(user);
    }

    @Override
    public List<User> getAll() {
	return repository.findAll();
    }

    @Override
    public User getByUsername(String username) {
	return repository.findByUsername(username);
    }

    @Override
    public User update(String username, User user) {
	User persistedUser = repository.findByUsername(username);
	Assert.notNull(persistedUser, "There is no user registered with this username");
	user.setUsername(username);
	return repository.save(user);
    }

    @Override
    public void deleteByUsername(String username) {
	User persistedUser = repository.findByUsername(username);
	Assert.notNull(persistedUser, "There is no user registered with this username");
	repository.deleteByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	log.info("loadUserByUsername={}", username);
	User persistedUser = repository.findByUsername(username);	
	if (persistedUser == null) {
	    throw new UsernameNotFoundException("There is no user registered with this username");
	}
	log.info("username found");
	return persistedUser;

//		org.springframework.security.core.userdetails.User.builder()
//	// @formatter:off
//		.username(persistedUser.getUsername())
//		.password(persistedUser.getPassword())
//		.build();
	// @formatter:on		
    }

}
