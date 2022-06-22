package com.gusrubin.lab.crudhistorywithfromto.application.api.products;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gusrubin.lab.crudhistorywithfromto.domain.product.Product;
import com.gusrubin.lab.crudhistorywithfromto.domain.product.ProductCrudUseCase;

@RestController
@RequestMapping("api/products")
public class ProductsController {

	private final ProductCrudUseCase productCrud;

	public ProductsController(ProductCrudUseCase productCrudUseCase) {
		this.productCrud = productCrudUseCase;
	}

	@PostMapping
	public Product post(@RequestBody Product customer) {
		return productCrud.create(customer);
	}

	@GetMapping
	public List<Product> getAll() {
		return productCrud.readAll();
	}

	@GetMapping("{id}")
	public Product getById(@PathVariable("id") Long id) {
		return productCrud.readById(id);
	}

	@PutMapping("{id}")
	public Product put(@PathVariable("id") Long id, @RequestBody Product product) {
		return productCrud.updateById(id, product);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Long id) {
		productCrud.deleteById(id);
	}

}
