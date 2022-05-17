package com.gusrubin.lab.ldapauthentication.domain;

import java.util.List;

public interface AuthPort {
	
	boolean authenticate(String username, String password);
	
	List<String> searchUser(String username);

}
