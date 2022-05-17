package com.gusrubin.springauthorizationserver.domain.user;

import java.util.List;

public interface UserUseCase {
    
    User create(User user);
    
    List<User> getAll();
    
    User getByUsername(String username);
    
    User update(String username, User user);
    
    void deleteByUsername(String username);

}
