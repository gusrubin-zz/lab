package com.gusrubin.lab.ldapauthentication.domain;

import java.util.List;

/**
 * @startuml
 * Bob -> Alice
 * Alice --> Bob
 * Alice <- Bob
 * @enduml
 * @author gusrubin
 *
 */

public interface AuthUseCase {

	boolean authenticate(String username, String password);

	List<String> searchUser(String username);

}
