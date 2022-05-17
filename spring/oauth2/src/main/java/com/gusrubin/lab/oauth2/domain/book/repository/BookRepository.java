package com.gusrubin.lab.oauth2.domain.book.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.gusrubin.lab.oauth2.domain.book.Book;

public interface BookRepository {

	Optional<Book> findById(UUID id);
	
	List<Book> findByTitle(String title);

	void save(Book book);
	
	void delete(UUID id);

}
