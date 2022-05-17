package com.gusrubin.lab.ldapauthentication.domain;

import java.util.List;

public class SecurityService implements AuthUseCase {

	private final AuthPort authPort;

	public SecurityService(AuthPort authPort) {
		this.authPort = authPort;
	}

	@Override
	public boolean authenticate(String username, String password) {
		return this.authPort.authenticate(username, password);
	}

	@Override
	public List<String> searchUser(String username) {
		return this.authPort.searchUser(username);
	}

}
