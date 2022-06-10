package dao;

import java.util.List;


public interface IDAO<T> {
	public T get(String id);
	public List<T> getAll();
	public void save(T t);
	public void update(T t, T u);
	public void update(T t);
	public void delete(T t);
	
}
