package com.gusrubin.lab.crudhistorywithfromto.infrastructure.database.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gusrubin.lab.crudhistorywithfromto.infrastructure.database.entities.PurchaseEntity;

@Repository
public interface PurchaseRepository extends JpaRepository<PurchaseEntity, Long> {

	Optional<PurchaseEntity> findByIdIn(List<Long> ids);
	
	List<PurchaseEntity> findByCustomerId(Long customerId);

}
