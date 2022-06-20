package com.gusrubin.lab.crudhistory.infrastructure.database.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gusrubin.lab.crudhistory.infrastructure.database.entities.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
	
	Optional<List<ProductEntity>> findByIdIn(List<Long> ids);

}
