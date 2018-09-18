package org.develop.app.persistence.dao.impl;

import java.lang.reflect.Field;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.OneToMany;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Table;

import org.develop.app.persistence.dao.interfaces.IBaseDao;
import org.develop.app.persistence.utils.QueryParameter;

abstract class BaseDao<E, PK> implements IBaseDao<E, PK> {

	@PersistenceContext
	EntityManager entityManager;
	
	public void create(E entity){
	   entityManager.persist(entity);
	}
	
	public E merge(E entity) {
		return entityManager.merge(entity);		
	}

	public void removeById(Class<E> clazz, PK key) {
		E entity = findByKey(clazz, key);
		entityManager.remove(entity);		
	}	
	
	public void remove(E entity) {
		entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));		
	}

	public E findByKey(Class<E> clazz, PK key) {
		return entityManager.find(clazz, key);
	}
	
	@SuppressWarnings("unchecked")
	public List<E> findByParams(Class<E> clazz, List<QueryParameter> params) {
		
		String tablename = clazz.getAnnotation(Table.class) != null ? clazz.getAnnotation(Table.class).name() : clazz.getSimpleName();
		
		StringBuilder sb = new StringBuilder("SELECT ");
		
		boolean isPrimitiveId = false;
		
		@SuppressWarnings("rawtypes")
		Class keyClazz = null;
		
		try {
			keyClazz = clazz.getMethod("getId").getReturnType();
			isPrimitiveId = keyClazz.isPrimitive();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Field[] kfs = keyClazz.getDeclaredFields();
		Field[] fs = clazz.getDeclaredFields(); 
		
		for(int i=0; i < kfs.length; i++) {
			if(!kfs[i].getName().equals("serialVersionUID")) { sb.append(kfs[i].getName().toUpperCase()+", "); }
		}
		
		for(int i=0; i < fs.length; i++) {
			
			if(fs[i].isAnnotationPresent(OneToMany.class)) {
				continue;
			}
			
			if(fs[i].getName().equals("id") && isPrimitiveId) {
				sb.append(fs[i].getName().toUpperCase()+", ");
			}
			
			if(!fs[i].getName().equals("id")) {	sb.append(fs[i].getName().toUpperCase()+", "); }
		}
		
		if(sb.toString().endsWith(", ")) { sb.replace(sb.length()-2, sb.length(), " "); }
		
		sb.append("FROM "+tablename+" WHERE ");
		
		for(QueryParameter param : params) {
			sb.append(param.getKey().toUpperCase()+"=" +
					(param.getValue() instanceof String ? "'" : "") +
					param.getValue() +
					(param.getValue() instanceof String ? "'" : "") +
					" AND ");
		}
		
		if(sb.toString().endsWith("AND ")) { sb.replace(sb.length()-4, sb.length(), " "); }
		
		Query query = entityManager.createNativeQuery(sb.toString(), clazz);
		
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<E> findAll(Class<E> clazz) {
		return entityManager.createQuery("from " + clazz.getName()).getResultList();
	}

}
