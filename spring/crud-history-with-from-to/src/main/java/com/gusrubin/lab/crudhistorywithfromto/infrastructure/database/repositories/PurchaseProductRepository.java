package com.gusrubin.lab.crudhistorywithfromto.infrastructure.database.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gusrubin.lab.crudhistorywithfromto.infrastructure.database.entities.PurchaseProductEntity;

@Repository
public interface PurchaseProductRepository extends JpaRepository<PurchaseProductEntity, Long> {

	Optional<PurchaseProductEntity> findByIdIn(List<Long> ids);
	
	List<PurchaseProductEntity> findByPurchaseId(Long purchaseId);

}
