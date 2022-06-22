package com.gusrubin.lab.crudhistorywithfromto.application.api.purchases;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePurchaseDto {
	
	private Long customerId;
	private List<Long> productIdList;

}
