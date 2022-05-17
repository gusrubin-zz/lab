package com.gusrubin.lab.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.gusrubin.lab.oauth2.application.cli.BookCliController;

@SpringBootApplication
public class Oauth2Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Oauth2Application.class, args);
	}

	@Autowired
	public BookCliController bookController;

	@Override
	public void run(String... args) throws Exception {
		bookController.createReadAndRemoveBook();
	}

}
