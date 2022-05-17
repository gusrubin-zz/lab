package com.gusrubin.lab.oauth2.application.rest;

import java.util.UUID;

public class BookDTO {

	private UUID id;

	private String author;

	private String title;

	public BookDTO(UUID id, String author, String title) {
		this.id = id;
		this.author = author;
		this.title = title;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", author=" + author + ", title=" + title + "]";
	}

}
