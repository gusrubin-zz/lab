package com.gusrubin.lab.java.biblioteca.backend.repository.commons.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class FileUtils {

	private static final Logger log = Logger.getLogger(FileUtils.class.getName());

	private FileUtils() {
		throw new IllegalStateException("Utility class");
	}
	
	public static void checkIfFileExistsOrCreatesIt(String fileName) {
		Path path = Paths.get(fileName);
        if (!Files.isRegularFile(path)) {
			try {
				Files.createFile(path);
			} catch (IOException e) {
				log.severe("Failed to create file " + fileName);
			}
		}
	}
	
	public static void writeFileInLine(String fileName, String line) {		
		Path path = Paths.get(fileName);
		byte[] buf = line.getBytes();
		
		try {
			Files.write(path, buf);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static List<String> readFileInList(String fileName) {
		List<String> lines = Collections.emptyList();
		try {
			lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
		} catch (IOException e) {
			log.severe("The file " + fileName + " is not found.");
		}
		return lines;
	}
	
	public static String readFileAsString(String fileName) {
		String lines = null;
		try {
			lines = Files.readString(Paths.get(fileName), StandardCharsets.UTF_8);
		} catch (IOException e) {
			log.severe("The file " + fileName + " is not found.");
		}
		return lines;
	}

}
