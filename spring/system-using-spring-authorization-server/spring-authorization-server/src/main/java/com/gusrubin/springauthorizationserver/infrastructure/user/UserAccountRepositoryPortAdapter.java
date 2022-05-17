package com.gusrubin.springauthorizationserver.infrastructure.user;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gusrubin.springauthorizationserver.domain.role.UserRole;
import com.gusrubin.springauthorizationserver.domain.user.User;
import com.gusrubin.springauthorizationserver.domain.user.UserRepositoryPort;
import com.gusrubin.springauthorizationserver.infrastructure.role.UserRoleEntity;

@Component
public class UserAccountRepositoryPortAdapter implements UserRepositoryPort {

    private final UserAccountRepository repository;
    private final ModelMapper mapper;

    @Autowired
    public UserAccountRepositoryPortAdapter(UserAccountRepository userRepository, ModelMapper modelMapper) {
	this.repository = userRepository;
	this.mapper = modelMapper;
    }

    @Override
    public User save(User user) {
	UserAccountEntity entity = mapper.map(user, UserAccountEntity.class);
	return mapper.map(repository.save(entity), User.class);
    }

    @Override
    public List<User> findAll() {
	return repository.findAll().stream().map(entity -> mapper.map(entity, User.class)).toList();
    }

    @Override
    public User findByUsername(String username) {
	User user = null;
	UserAccountEntity entity = repository.findByUsername(username);
	if (entity != null) {
	    user = User.builder()
	    // @formatter:off
		    .id(entity.getId())
		    .name(entity.getName())
		    .username(entity.getUsername())
		    .password(entity.getPassword())
		    .roles(entity.getRoles().stream().map(this::toUserRole).toList())
		    .accountExpired(entity.getAccountExpired())
		    .accountLocked(entity.getAccountLocked())
		    .credentialsExpired(entity.getCredentialsExpired())
		    .enabled(entity.getEnabled())
		    .build();
	    // @formatter:on
	}
	return user;
    }

    private UserRole toUserRole(UserRoleEntity entity) {
	return UserRole.builder().id(entity.getId()).roleName(entity.getRole()).build();
    }

    @Override
    public void deleteByUsername(String username) {
	repository.deleteByUsername(username);
    }

}
