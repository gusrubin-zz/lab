package com.gusrubin.lab.cachingrestapi.infrastructure.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
	
	Optional<UserEntity> findById(Long id);
	
	void deleteById(Long id);

}
