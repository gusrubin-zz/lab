package com.gusrubin.lab.oauth2.domain.book.service;

import java.util.UUID;

import com.gusrubin.lab.oauth2.domain.book.Book;

public interface BookService {

	Book createBook(String author, String title);
	
	Book findBookById(UUID id);

	void deleteBook(UUID id);

}
