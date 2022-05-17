package com.gusrubin.lab.oauth2.infrastructure.configurations;

import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.gusrubin.lab.oauth2.infrastructure.repository.mongo.SpringDataMongoBookRepository;

@EnableMongoRepositories(basePackageClasses = SpringDataMongoBookRepository.class)
public class MongoDBConfiguration {

}
