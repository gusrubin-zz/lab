package com.gusrubin.lab.crudhistorywithfromto.application.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;

@RestControllerAdvice
public class RestExceptionHandler {

	/**
	 * Handle constraint validation exceptions during object creation when requires
	 * values are not provided in the body of the request.
	 *
	 * @param ex the {@link IllegalArgumentException}
	 * @return a Map with error messages with 400 HTTP status
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(IllegalArgumentException.class)
	public Map<String, String> handleIllegalArgumentException(IllegalArgumentException ex) {
		Map<String, String> errors = new HashMap<>();
		String errorMessage = ex.getMessage();
		errors.put("error", errorMessage);
		return errors;
	}

	/**
	 * Handle constraint validation exceptions during the process has a wrong state.
	 *
	 * @param ex the {@link IllegalStateException}
	 * @return a Map with error messages with 400 HTTP status
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(IllegalStateException.class)
	public Map<String, String> handleIllegalStateException(IllegalStateException ex) {
		Map<String, String> errors = new HashMap<>();
		String errorMessage = ex.getMessage();
		errors.put("error", errorMessage);
		return errors;
	}

}
