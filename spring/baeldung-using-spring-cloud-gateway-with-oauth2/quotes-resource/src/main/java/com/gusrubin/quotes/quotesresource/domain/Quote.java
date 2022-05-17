package com.gusrubin.quotes.quotesresource.domain;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Quote {
	
	private String symbol;
	private BigDecimal price;

}
