package com.gusrubin.lab.crudhistory.infrastructure.database.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "purchase")
public class PurchaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "customer_id")
	private Long customerId;
	@Column(name = "created_at")
	private LocalDateTime createdAt;
//	@OneToMany(cascade = CascadeType.ALL)//, mappedBy = "purchaseId", orphanRemoval = true)
//	@JoinColumn(name = "purchase_id")
//	private List<PurchaseProductsEntity> productList;

}
