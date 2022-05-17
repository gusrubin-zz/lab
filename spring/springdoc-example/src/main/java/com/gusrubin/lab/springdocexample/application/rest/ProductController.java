package com.gusrubin.lab.springdocexample.application.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gusrubin.lab.springdocexample.domain.product.Product;
import com.gusrubin.lab.springdocexample.domain.product.ProductUseCase;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
public class ProductController {

	private final ProductUseCase productUseCase;

	@Autowired
	public ProductController(ProductUseCase productUseCase) {
		this.productUseCase = productUseCase;
	}

	@GetMapping
	public Mono<List<Product>> getProducts() {
		return Mono.just(productUseCase.findAll());
	}

	@GetMapping("{id}")
	public Mono<Product> getProductById(@RequestParam("id") Long id) {
		return Mono.just(productUseCase.findById(id));
	}

	@GetMapping("/name/{name}")
	public Mono<Product> getProductByName(@RequestParam("name") String name) {
		return Mono.just(productUseCase.findByName(name));
	}

	@PostMapping
	public Mono<Product> postProduct(@RequestBody Product product) {
		return Mono.just(productUseCase.create(product));
	}

	@PutMapping("{id}")
	public Mono<Product> putProduct(@RequestParam("id") Long id, @RequestBody Product product) {
		return Mono.just(productUseCase.update(id, product));
	}

	@DeleteMapping("{id}")
	public Mono<Void> deleteProductById(@RequestParam("id") Long id) {
		productUseCase.deleteById(id);
		return Mono.empty();
	}

}
