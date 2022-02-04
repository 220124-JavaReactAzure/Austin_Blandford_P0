package com.revature.bank_app.daos;

import com.revature.bank_app.util.List;

public interface CrudDAO<T> {
	
	// Create
	T create(T newObject);
	
	// Read
	List<T> findAll();
	T findById();
	
	// Update
	boolean update(T updatedObject);
	
	// Delete
	boolean delete(String id);
	
}
