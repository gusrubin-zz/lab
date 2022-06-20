package com.gusrubin.lab.crudhistory.application.api.purchases;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gusrubin.lab.crudhistory.domain.purchase.Purchase;
import com.gusrubin.lab.crudhistory.domain.purchase.PurchaseUseCases;

@RestController
@RequestMapping("api/purchases")
public class PurchasesController {

	private final PurchaseUseCases purchaseUseCases;

	public PurchasesController(PurchaseUseCases purchaseUseCases) {
		this.purchaseUseCases = purchaseUseCases;
	}

	@PostMapping
	public Purchase post(@RequestBody CreatePurchaseDto createPurchaseDto) {
		return purchaseUseCases.create(createPurchaseDto.getCustomerId(), createPurchaseDto.getProductIdList());
	}

	@GetMapping
	public List<Purchase> getAll() {
		return purchaseUseCases.readAll();
	}

	@GetMapping("ids/{ids}")
	public List<Purchase> getByIdIn(@RequestParam List<Long> ids) {
		return purchaseUseCases.readByIdIn(ids);
	}

	@GetMapping("{id}")
	public Purchase getById(@PathVariable("id") Long id) {
		return purchaseUseCases.readById(id);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Long id) {
		purchaseUseCases.deleteById(id);
	}

}
