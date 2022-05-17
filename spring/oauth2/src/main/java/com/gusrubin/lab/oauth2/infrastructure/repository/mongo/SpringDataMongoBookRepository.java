package com.gusrubin.lab.oauth2.infrastructure.repository.mongo;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gusrubin.lab.oauth2.domain.book.Book;

@Repository
public interface SpringDataMongoBookRepository extends MongoRepository<Book, UUID> {

}
