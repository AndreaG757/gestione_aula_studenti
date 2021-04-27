package it.com.gestioneaulastudenti.dao;

import java.util.List;

public interface IBaseDAO<T> {

	public List<T> list();
	public T get(Long id);
	public void update(T object);
	public void insert(T object);
	public void delete(T object);
	public List<T> findByExample(T object);
	
}
