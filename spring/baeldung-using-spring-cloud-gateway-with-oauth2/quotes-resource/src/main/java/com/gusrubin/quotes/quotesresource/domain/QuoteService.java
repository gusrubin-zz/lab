package com.gusrubin.quotes.quotesresource.domain;

import java.math.BigDecimal;

public class QuoteService implements QuoteUseCase {

	@Override
	public Quote getNormalQuote() {
		return Quote.builder().symbol("Normal").price(BigDecimal.valueOf(12.9)).build();
	}

	@Override
	public Quote getGoldQuote() {
		return Quote.builder().symbol("Gold").price(BigDecimal.valueOf(10.5)).build();
	}

}
