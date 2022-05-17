package com.gusrubin.lab.cachingrestapi.infrastructure.user;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.gusrubin.lab.cachingrestapi.domain.user.User;
import com.gusrubin.lab.cachingrestapi.domain.user.UserRepositoryPort;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@CacheConfig(cacheNames = { "users" })
public class UserRepositoryAdapter implements UserRepositoryPort {

	private final UserRepository repository;

	private final ModelMapper mapper;

	@Autowired
	public UserRepositoryAdapter(UserRepository userRepository, ModelMapper modelMapper) {
		this.repository = userRepository;
		this.mapper = modelMapper;
	}

	@Override
	@Cacheable
	public List<User> findAll() {
		log.info("hit repository findAll");
		return repository.findAll().stream().map(user -> mapper.map(user, User.class)).collect(Collectors.toList());
	}

	@Override
	@Cacheable(key = "#id")
	public User findById(Long id) {
		log.info("hit repository findById");
		UserEntity entity = repository.findById(id).orElse(null);
		User user = null;
		if (entity != null) {
			user = mapper.map(entity, User.class);
		}
		return user;
	}

	@Override
	@CacheEvict(allEntries = true)
	public User create(User user) {
		return mapper.map(repository.save(mapper.map(user, UserEntity.class)), User.class);
	}

	@Override
	@CacheEvict(key = "#id")
	public void deleteById(Long id) {
		log.info("hit repository deleteById");
		repository.deleteById(id);
	}

}
