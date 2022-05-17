package com.gusrubin.springauthorizationserver.infrastructure.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccountEntity, Long> {
    
    UserAccountEntity findByUsername(String username);
    
    void deleteByUsername(String username);

}
