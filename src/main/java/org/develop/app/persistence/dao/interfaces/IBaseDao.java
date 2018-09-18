package org.develop.app.persistence.dao.interfaces;

import java.util.List;

import org.develop.app.persistence.utils.QueryParameter;

public interface IBaseDao<E, PK> {
	
	public void create(E entity);
	
	public E merge(E entity);
	
	public void removeById(Class<E> clazz, PK key);
	
	public void remove(E entity);
	
	public E findByKey(Class<E> clazz, PK key);
	
	public List<E> findByParams(Class<E> clazz, List<QueryParameter> params);
	
	public List<E> findAll(Class<E> clazz);
	
}
