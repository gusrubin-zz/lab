package com.gusrubin.lab.java.biblioteca;

import java.util.logging.Logger;

import com.gusrubin.lab.java.biblioteca.frontend.ApplicationConsole;

public class BibliotecaApplication {

	private static final Logger log = Logger.getLogger(BibliotecaApplication.class.getName());

	public static void main(String[] args) {
		log.info("Application started");
		new ApplicationConsole().mostraMenuPrincipal();
	}

}
