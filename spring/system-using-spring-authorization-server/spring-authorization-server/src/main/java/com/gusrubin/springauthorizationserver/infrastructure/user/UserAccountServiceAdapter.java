package com.gusrubin.springauthorizationserver.infrastructure.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gusrubin.springauthorizationserver.domain.user.UserService;

@Service
public class UserAccountServiceAdapter extends UserService {

    @Autowired
    public UserAccountServiceAdapter(UserAccountRepositoryPortAdapter userRepositoryPortAdapter) {
	super(userRepositoryPortAdapter);
    }

}
