package com.gusrubin.lab.oauth2.application.rest;

import com.gusrubin.lab.oauth2.domain.book.Book;

public class BookMapper {

	private BookMapper() {
		throw new IllegalStateException("Utility class");
	}

	public static Book toBook(BookDTO bookDTO) {
		return new Book(bookDTO.getId() != null ? bookDTO.getId() : null,
				bookDTO.getAuthor() != null ? bookDTO.getAuthor() : null,
				bookDTO.getTitle() != null ? bookDTO.getTitle() : null);
	}

	public static BookDTO toBookDTO(Book book) {
		return new BookDTO(book.getId() != null ? book.getId() : null,
				book.getAuthor() != null ? book.getAuthor() : null, book.getTitle() != null ? book.getTitle() : null);
	}

}
