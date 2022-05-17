package com.gusrubin.lab.springdocexample.infrastructure.product;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
	
	Optional<ProductEntity> findByName(String name);
	
	void deleteById(Long id);

}
