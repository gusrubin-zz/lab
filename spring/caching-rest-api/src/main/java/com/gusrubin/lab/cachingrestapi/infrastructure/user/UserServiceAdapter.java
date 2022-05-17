package com.gusrubin.lab.cachingrestapi.infrastructure.user;

import org.springframework.stereotype.Service;

import com.gusrubin.lab.cachingrestapi.domain.user.UserRepositoryPort;
import com.gusrubin.lab.cachingrestapi.domain.user.UserService;

@Service
public class UserServiceAdapter extends UserService {

	public UserServiceAdapter(UserRepositoryPort userRepositoryPort) {
		super(userRepositoryPort);
	}

}
