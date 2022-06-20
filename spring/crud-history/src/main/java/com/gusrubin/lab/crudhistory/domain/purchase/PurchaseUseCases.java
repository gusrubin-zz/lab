package com.gusrubin.lab.crudhistory.domain.purchase;

import java.util.List;

public interface PurchaseUseCases {

	Purchase create(Long customerId, List<Long> productIds);

	List<Purchase> readAll();

	List<Purchase> readByIdIn(List<Long> ids);

	Purchase readById(Long id);

	List<Purchase> readByCustomerId(Long customerId);

	void deleteById(Long id);

}
