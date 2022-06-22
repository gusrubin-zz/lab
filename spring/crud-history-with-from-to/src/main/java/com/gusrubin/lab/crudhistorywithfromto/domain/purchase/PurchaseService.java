package com.gusrubin.lab.crudhistorywithfromto.domain.purchase;

import java.util.List;

import org.springframework.util.Assert;

import com.gusrubin.lab.crudhistorywithfromto.domain.customer.Customer;
import com.gusrubin.lab.crudhistorywithfromto.domain.customer.CustomerCrudUseCase;
import com.gusrubin.lab.crudhistorywithfromto.domain.product.Product;
import com.gusrubin.lab.crudhistorywithfromto.domain.product.ProductCrudUseCase;

public class PurchaseService implements PurchaseUseCases {

	private final CustomerCrudUseCase customerCrudUseCase;
	private final ProductCrudUseCase productCrudUseCase;
	private final PurchaseRepositoryPort purchaseRepository;

	public PurchaseService(CustomerCrudUseCase customerCrudUseCase, ProductCrudUseCase productCrudUseCase,
			PurchaseRepositoryPort purchaseRepositoryPort) {
		this.customerCrudUseCase = customerCrudUseCase;
		this.productCrudUseCase = productCrudUseCase;
		this.purchaseRepository = purchaseRepositoryPort;
	}

	@Override
	public Purchase create(Long customerId, List<Long> productIds) {
		// Validations
		Assert.notNull(customerId, "Customer id can't be null");
		Customer customer = customerCrudUseCase.readById(customerId);
		Assert.notNull(customer, "Customer not registered");
		Assert.notEmpty(productIds, "Purchase must have at least one product id");
		List<Product> productList = productCrudUseCase.readByIdIn(productIds);
		Assert.notEmpty(productList, "Products are not registered");

		// Prepare

		// Performs method goals
		return purchaseRepository.save(Purchase.create(customerId, productList));
	}

	@Override
	public List<Purchase> readAll() {
		// Validations

		// Prepare

		// Performs method goals
		return purchaseRepository.findAll();
	}

	@Override
	public List<Purchase> readByIdIn(List<Long> ids) {
		// Validations
		Assert.notEmpty(ids, "Id list must have at least one purchase id");

		// Prepare

		// Performs method goals
		return purchaseRepository.findByIdIn(ids);
	}

	@Override
	public Purchase readById(Long id) {
		// Validations
		Assert.notNull(id, "Purchase id can't be null");
		Purchase persistedPurchase = purchaseRepository.findById(id);
		Assert.notNull(persistedPurchase, "Purchase not registered");

		// Prepare

		// Performs method goals
		return persistedPurchase;
	}

	@Override
	public List<Purchase> readByCustomerId(Long customerId) {
		// Validations
		Assert.notNull(customerId, "Customer id can't be null");
		List<Purchase> persistedPurchaseList = purchaseRepository.findByCustomerId(customerId);
		Assert.notEmpty(persistedPurchaseList, "Purchase not registered");

		// Prepare

		// Performs method goals
		return persistedPurchaseList;
	}

	@Override
	public void deleteById(Long id) {
		// Validations
		Assert.notNull(id, "Purchase id can't be null");
		Purchase persistedPurchase = purchaseRepository.findById(id);
		Assert.notNull(persistedPurchase, "Purchase not registered");

		// Performs method goals
		purchaseRepository.deleteById(id);
	}

}
