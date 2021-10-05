package com.revature.project0.daos;

import java.util.List;

public interface GenericDao <T>{
	List<T> getAll();
	T getByName(String name);
	T getById(int id);
	void update(T entity);
	T insert(T entity);
	void delete(T entity);
}
