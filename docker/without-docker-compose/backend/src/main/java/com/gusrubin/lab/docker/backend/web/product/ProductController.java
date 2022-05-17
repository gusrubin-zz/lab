package com.gusrubin.lab.docker.backend.web.product;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gusrubin.lab.docker.backend.domain.product.Product;
import com.gusrubin.lab.docker.backend.domain.product.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/products")
	public ResponseEntity<List<ProductDto>> getAll() {
		List<ProductDto> products = new ArrayList<>();
		productService.findAll().stream().forEach(product -> {
			products.add(new ProductDto(product).build());
		});
		return ResponseEntity.ok(products);
	}

	@GetMapping("/products/{productId}")
	public ResponseEntity<ProductDto> getProductById(@PathVariable("productId") String productId) {
		ProductDto responseBody = new ProductDto(productService.findById(productId));
		return ResponseEntity.ok(responseBody);
	}

	@PostMapping("/products")
	public ResponseEntity<ProductDto> postProduct(@RequestBody ProductDto requestBody) {
		Product product = productService.create(new Product(requestBody.getName(), requestBody.getDescription()));
		return ResponseEntity.ok(new ProductDto(product));
	}

	@DeleteMapping("/products/{productId}")
	public ResponseEntity<Void> deleteProduct(@PathVariable("productId") String productId) {
		productService.delete(productId);
		return ResponseEntity.noContent().build();
	}

}
