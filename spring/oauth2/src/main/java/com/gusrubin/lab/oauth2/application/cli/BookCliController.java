package com.gusrubin.lab.oauth2.application.cli;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gusrubin.lab.oauth2.domain.book.Book;
import com.gusrubin.lab.oauth2.domain.book.service.BookService;

@Component
public class BookCliController {

	private static final Logger LOG = LoggerFactory.getLogger(BookCliController.class);

	private final BookService bookService;

	@Autowired
	public BookCliController(BookService bookService) {
		this.bookService = bookService;
	}

	public void createReadAndRemoveBook() {
		LOG.info("<<Create, read and remove book>>");
		Book book = createBook();
		showBookInfo(book.getId());
		deleteBook(book.getId());
	}

	private void showBookInfo(UUID id) {
		Book book = bookService.findBookById(id);
		LOG.info("<<Book information>> {}", book);
	}

	private Book createBook() {
		LOG.info("Creating book");
		return bookService.createBook("Leandro Karnal", "Filosofia Para Todos");
	}

	private void deleteBook(UUID id) {
		LOG.info("Deleting book");
		bookService.deleteBook(id);
	}

}
