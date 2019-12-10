package com.telefonia.utils;

import java.util.List;

import org.hibernate.Session;

public abstract class AbstractFacade<T> {

	private Session session = HibernateUtil.getSessionFactory().openSession();

	private Class<T> entityClass;

	public AbstractFacade(Class<T> entityClass) {
		super();
		this.entityClass = entityClass;
	}

	public void save(T entity) {
		
		try {
			session.beginTransaction();
			session.save(entity);
			session.flush();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.clear();
		}
	}

	public void delete(T entity) {
		session.beginTransaction();
		session.delete(entity);
		session.flush();
		session.getTransaction().commit();
	}

	public T readById(int id) {
		session.beginTransaction();
		T t = session.get(entityClass, id);
		session.flush();
		session.getTransaction().commit();
		return t;
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		session.beginTransaction();
		List<T> list = session.createQuery("FROM " + entityClass.getName()).list();
		session.flush();
		session.getTransaction().commit();
		return list;
	}

	public void update(T entity) {
		session.beginTransaction();
		session.merge(entity);
		session.flush();
		session.getTransaction().commit();
	}
}