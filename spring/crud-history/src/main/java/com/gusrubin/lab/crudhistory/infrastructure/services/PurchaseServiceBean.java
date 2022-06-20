package com.gusrubin.lab.crudhistory.infrastructure.services;

import org.springframework.stereotype.Service;

import com.gusrubin.lab.crudhistory.domain.customer.CustomerCrudUseCase;
import com.gusrubin.lab.crudhistory.domain.product.ProductCrudUseCase;
import com.gusrubin.lab.crudhistory.domain.purchase.PurchaseRepositoryPort;
import com.gusrubin.lab.crudhistory.domain.purchase.PurchaseService;

@Service
public class PurchaseServiceBean extends PurchaseService {

	public PurchaseServiceBean(CustomerCrudUseCase customerCrudUseCase, ProductCrudUseCase productCrudUseCase,
			PurchaseRepositoryPort purchaseRepositoryPort) {
		super(customerCrudUseCase, productCrudUseCase, purchaseRepositoryPort);
	}

}
