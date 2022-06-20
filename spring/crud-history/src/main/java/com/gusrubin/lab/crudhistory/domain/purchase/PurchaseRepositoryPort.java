package com.gusrubin.lab.crudhistory.domain.purchase;

import java.util.List;

public interface PurchaseRepositoryPort {

	Purchase save(Purchase purchase);

	List<Purchase> findAll();

	List<Purchase> findByIdIn(List<Long> ids);

	Purchase findById(Long id);
	
	List<Purchase> findByCustomerId(Long customerId);

	void deleteById(Long id);

}
