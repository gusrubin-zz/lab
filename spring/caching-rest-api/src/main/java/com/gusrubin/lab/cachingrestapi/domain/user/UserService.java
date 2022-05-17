package com.gusrubin.lab.cachingrestapi.domain.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class UserService implements UserUseCase {

	private final UserRepositoryPort userRepository;

	@Autowired
	public UserService(UserRepositoryPort userRepositoryPort) {
		this.userRepository = userRepositoryPort;
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User findById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public User create(User user, String password) {
		User newUser = User.builder().username(user.getUsername()).passwordHash(password + 1).build();
		return userRepository.create(newUser);
	}

	@Override
	public void deleteById(Long id) {
		User user = userRepository.findById(id);
		if (user == null) {
			throw new IllegalStateException("User id doesnt exist");
		}
		userRepository.deleteById(id);
	}

}
