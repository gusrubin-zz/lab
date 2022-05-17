package com.gusrubin.lab.oauth2.domain.book.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.gusrubin.lab.oauth2.domain.book.Book;
import com.gusrubin.lab.oauth2.domain.book.repository.BookRepository;

@Service
public class DomainBookService implements BookService {

	private final BookRepository bookRepository;

	public DomainBookService(final BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	@Override
	public Book createBook(final String author, final String title) {
		final Book book = new Book(UUID.randomUUID(), author, title);
		bookRepository.save(book);
		return book;
	}

	@Override
	public Book findBookById(UUID id) {
		return getBook(id);
	}

	@Override
	public void deleteBook(final UUID id) {
		getBook(id);
		bookRepository.delete(id);
	}

	private Book getBook(UUID id) {
		return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book with given id doesn't exist"));
	}

}
