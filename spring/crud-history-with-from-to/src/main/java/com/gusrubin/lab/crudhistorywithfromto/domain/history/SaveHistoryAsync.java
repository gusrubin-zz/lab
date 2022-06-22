package com.gusrubin.lab.crudhistorywithfromto.domain.history;

import org.springframework.scheduling.annotation.Async;

public interface SaveHistoryAsync<T> {

	/**
	 * Gets the context from class name of T object
	 * 
	 * @param isSuccessful
	 * @param object
	 */
	@Async
	void saveCreationInHistory(boolean isSuccessful, T object);

	/**
	 * 
	 * @param context
	 * @param isSuccessful
	 * @param object
	 */
	@Async
	void saveCreationInHistory(String context, boolean isSuccessful, T object);

	/**
	 * 
	 * @param isSuccessful
	 * @param objectA
	 * @param objectB
	 */
	@Async
	void saveChangeInHistory(boolean isSuccessful, T objectA, T objectB);

	/**
	 * 
	 * @param context
	 * @param isSuccessful
	 * @param objectA
	 * @param objectB
	 */
	@Async
	void saveChangeInHistory(String context, boolean isSuccessful, T objectA, T objectB);

	/**
	 * Gets the context from class name of T object
	 * 
	 * @param isSuccessful
	 * @param object
	 */
	@Async
	void saveExclusionInHistory(boolean isSuccessful, T object);

	/**
	 * 
	 * @param context
	 * @param isSuccessful
	 * @param object
	 */
	@Async
	void saveExclusionInHistory(String context, boolean isSuccessful, T object);

}
