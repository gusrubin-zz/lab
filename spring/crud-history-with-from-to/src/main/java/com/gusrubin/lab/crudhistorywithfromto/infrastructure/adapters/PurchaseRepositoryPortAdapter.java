package com.gusrubin.lab.crudhistorywithfromto.infrastructure.adapters;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.gusrubin.lab.crudhistorywithfromto.domain.product.Product;
import com.gusrubin.lab.crudhistorywithfromto.domain.purchase.Purchase;
import com.gusrubin.lab.crudhistorywithfromto.domain.purchase.PurchaseRepositoryPort;
import com.gusrubin.lab.crudhistorywithfromto.infrastructure.database.entities.ProductEntity;
import com.gusrubin.lab.crudhistorywithfromto.infrastructure.database.entities.PurchaseEntity;
import com.gusrubin.lab.crudhistorywithfromto.infrastructure.database.entities.PurchaseProductEntity;
import com.gusrubin.lab.crudhistorywithfromto.infrastructure.database.repositories.ProductRepository;
import com.gusrubin.lab.crudhistorywithfromto.infrastructure.database.repositories.PurchaseProductRepository;
import com.gusrubin.lab.crudhistorywithfromto.infrastructure.database.repositories.PurchaseRepository;

@Component
public class PurchaseRepositoryPortAdapter implements PurchaseRepositoryPort {

	private final PurchaseRepository purchaseRepository;
	private final PurchaseProductRepository purchaseProductRepository;
	private final ProductRepository productRepository;
	private final ModelMapper mapper;

	public PurchaseRepositoryPortAdapter(PurchaseRepository purchaseRepository,
			PurchaseProductRepository purchaseProductRepository, ProductRepository productRepository,
			ModelMapper modelMapper) {
		this.purchaseRepository = purchaseRepository;
		this.purchaseProductRepository = purchaseProductRepository;
		this.productRepository = productRepository;
		this.mapper = modelMapper;
	}

	public PurchaseProductEntity toPurchaseProductsEntity(PurchaseEntity purchaseEntity, Product product) {
		return PurchaseProductEntity.builder()
		// @formatter:off
				.productId(product.getId() != null ? product.getId(): null)
				.price(product.getPrice() != null ? product.getPrice(): null)
				.purchase(purchaseEntity)
				.build();
	    // @formatter:on
	}

	public Product toProduct(PurchaseProductEntity purchaseProductsEntity, String name, String description) {
		return Product.builder()
		// @formatter:off
				.id(purchaseProductsEntity.getProductId() != null ? purchaseProductsEntity.getProductId() : null)
				.name(name)
				.description(description)
				.price(purchaseProductsEntity.getPrice() != null ? purchaseProductsEntity.getPrice() : null)
				.build();
		// @formatter:on
	}

	@Override
	@Transactional
	public Purchase save(Purchase purchase) {
		PurchaseEntity persistedPurchaseEntity = purchaseRepository.save(mapper.map(purchase, PurchaseEntity.class));

		List<Product> productList = new ArrayList<>();
		purchase.getProducts().stream().forEach(product -> {
			productList.add(toProduct(
					purchaseProductRepository.save(toPurchaseProductsEntity(persistedPurchaseEntity, product)),
					product.getName(), product.getDescription()));
		});

		return Purchase.create(purchase.getCustomerId(), productList);
	}

	@Override
	@Transactional
	public List<Purchase> findAll() {
		List<Purchase> purchaseList = new ArrayList<>();

		purchaseRepository.findAll().stream().forEach(p -> {
			List<PurchaseProductEntity> purchaseProduct = purchaseProductRepository.findByPurchaseId(p.getId());
			List<Product> products = new ArrayList<>();
			purchaseProduct.stream().forEach(e -> {
				ProductEntity productEntity = productRepository.findById(e.getProductId()).orElse(null);

				if (productEntity != null) {
					products.add(Product.builder()
					// @formatter:off
							.id(productEntity.getId())
							.name(productEntity.getName())
							.description(productEntity.getDescription())
							.price(e.getPrice())
							.build());
					// @formatter:on							
				}
			});

			purchaseList.add(Purchase.builder()
			// @formatter:off
					.id(p.getId())
					.customerId(p.getCustomerId())
					.products(products)
					.createdAt(p.getCreatedAt())				
					.build());
			// @formatter:on			
		});

		return purchaseList;
	}

	@Override
	@Transactional
	public List<Purchase> findByIdIn(List<Long> ids) {
		return purchaseRepository.findByIdIn(ids).stream().map(c -> mapper.map(c, Purchase.class)).toList();
	}

	@Override
	@Transactional
	public Purchase findById(Long id) {
		PurchaseEntity persistedPurchaseEntity = purchaseRepository.findById(id).orElse(null);
		return persistedPurchaseEntity != null ? mapper.map(persistedPurchaseEntity, Purchase.class) : null;
	}

	@Override
	@Transactional
	public List<Purchase> findByCustomerId(Long customerId) {
		return purchaseRepository.findByCustomerId(customerId).stream().map(c -> mapper.map(c, Purchase.class))
				.toList();
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		purchaseRepository.deleteById(id);
	}

}
