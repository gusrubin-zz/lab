package com.gusrubin.lab.oauth2.infrastructure.repository.mongo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.gusrubin.lab.oauth2.domain.book.Book;
import com.gusrubin.lab.oauth2.domain.book.repository.BookRepository;

@Component
@Primary
public class MongoDbBookRepository implements BookRepository {

	private final SpringDataMongoBookRepository bookRepository;

	@Autowired
	public MongoDbBookRepository(final SpringDataMongoBookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	@Override
	public Optional<Book> findById(final UUID id) {
		return bookRepository.findById(id);
	}

	@Override
	public List<Book> findByTitle(final String title) {
		final List<Book> allBooks = bookRepository.findAll();
		return allBooks.stream().filter(b -> b.getTitle().equals(title)).collect(Collectors.toList());
	}

	@Override
	public void save(final Book book) {
		bookRepository.save(book);
	}

	@Override
	public void delete(final UUID id) {
		final Optional<Book> result = findById(id);
		if (result.isEmpty()) {
			throw new IllegalStateException("The given book id doesnt exist");
		}
		bookRepository.delete(result.get());
	}

}
