package com.gusrubin.lab.ldapauthentication.infrastructure.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gusrubin.lab.ldapauthentication.domain.AuthPort;
import com.gusrubin.lab.ldapauthentication.domain.SecurityService;

@Service
public class SecurityServiceAdapter extends SecurityService {

	@Autowired
	public SecurityServiceAdapter(AuthPort authPort) {
		super(authPort);
	}

}
