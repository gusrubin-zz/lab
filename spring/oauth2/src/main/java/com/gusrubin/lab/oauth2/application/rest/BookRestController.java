package com.gusrubin.lab.oauth2.application.rest;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gusrubin.lab.oauth2.domain.book.service.BookService;

@RestController
@RequestMapping("/books")
public class BookRestController {

	private final BookService bookService;

	@Autowired
	public BookRestController(final BookService bookService) {
		this.bookService = bookService;
	}

	@PostMapping
	public ResponseEntity<BookDTO> post(@RequestBody final BookDTO requestBody) {
		return ResponseEntity
				.ok(BookMapper.toBookDTO(bookService.createBook(requestBody.getAuthor(), requestBody.getTitle())));
	}

	@GetMapping("/{bookId}")
	public ResponseEntity<BookDTO> getBook(@PathVariable("bookId") final UUID bookId) {
		return ResponseEntity.ok(BookMapper.toBookDTO(bookService.findBookById(bookId)));
	}

	@DeleteMapping("/{bookId}")
	public ResponseEntity<Void> deleteBook(@PathVariable("bookId") final UUID bookId) {
		bookService.deleteBook(bookId);
		return ResponseEntity.noContent().build();
	}

}
