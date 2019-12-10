package com.telefonia.utils;

import java.util.List;

public interface Dao<T> {

	public void save(T t);

	public T readById(int id);

	public void update(T t);

	public void delete(T t);

	List<T> findAll();
}
