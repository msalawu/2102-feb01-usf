package com.revature.data;

import java.util.Set;

public interface GenericDAO <T> {
	// CRUD operations (create, read, update, delete)
	public T add(T t) throws Exception;
	public T getById(Integer id);
	public Set<T> getAll();
	public void update(T t);
	public void delete(T t);
}
