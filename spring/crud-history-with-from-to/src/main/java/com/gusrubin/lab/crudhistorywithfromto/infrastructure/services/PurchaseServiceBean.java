package com.gusrubin.lab.crudhistorywithfromto.infrastructure.services;

import org.springframework.stereotype.Service;

import com.gusrubin.lab.crudhistorywithfromto.domain.customer.CustomerCrudUseCase;
import com.gusrubin.lab.crudhistorywithfromto.domain.product.ProductCrudUseCase;
import com.gusrubin.lab.crudhistorywithfromto.domain.purchase.PurchaseRepositoryPort;
import com.gusrubin.lab.crudhistorywithfromto.domain.purchase.PurchaseService;

@Service
public class PurchaseServiceBean extends PurchaseService {

	public PurchaseServiceBean(CustomerCrudUseCase customerCrudUseCase, ProductCrudUseCase productCrudUseCase,
			PurchaseRepositoryPort purchaseRepositoryPort) {
		super(customerCrudUseCase, productCrudUseCase, purchaseRepositoryPort);
	}

}
