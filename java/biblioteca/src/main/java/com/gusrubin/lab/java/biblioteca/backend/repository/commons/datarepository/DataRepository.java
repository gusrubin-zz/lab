package com.gusrubin.lab.java.biblioteca.backend.repository.commons.datarepository;

import java.util.List;
import java.util.Optional;

public interface DataRepository<T> {

	T save(T entity);

	List<T> findAll();
	
	Optional<T> findById(Integer id);

	void delete(Integer id);
}
