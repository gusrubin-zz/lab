package com.gusrubin.springauthorizationserver.domain.user;

import java.util.List;

public interface UserRepositoryPort {

    User save(User user);

    List<User> findAll();

    User findByUsername(String username);

    void deleteByUsername(String username);

}
