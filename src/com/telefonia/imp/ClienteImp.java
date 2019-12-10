package com.telefonia.imp;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.telefonia.models.Clientes;
import com.telefonia.utils.AbstractFacade;
import com.telefonia.utils.Dao;
import com.telefonia.utils.HibernateUtil;

@Repository
public class ClienteImp extends AbstractFacade<Clientes> implements Dao<Clientes> {

	private Session session = HibernateUtil.getSessionFactory().openSession();

	public ClienteImp() {
		super(Clientes.class);
	}

	// Metodo que cuenta el codigo Maximo del cliente y si no retorna 1000 como
	// numero de inicio
	public int codigoCliente() {
		
		session.beginTransaction();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Integer> cq = cb.createQuery(Integer.class);
		Root<Clientes> root = cq.from(Clientes.class);

		cq.select(cb.max(root.get("codigoCl")));

		Query<Integer> q = session.createQuery(cq);
		q.uniqueResult();
		
		if(q.uniqueResult()==null)
		{
			session.flush();
			session.getTransaction().commit();
			return 1000;
		}
		else
		{
			session.flush();
			session.getTransaction().commit();
			return q.uniqueResult()+1;
		}
	}

	// Metodo que busca por numero de dui
	public List<Clientes> clientesXDui(String dui) {

		try {
			session.beginTransaction();
			List<Clientes> cl = session.createNativeQuery(
					"select * from clientes inner join personas  on personas.id_per=clientes.persona where dui like '"
							+ dui + "%'",
					Clientes.class).getResultList();
			session.flush();
			session.getTransaction().commit();
			return cl;
		} catch (Exception e) {
			session.clear();
			return null;
		}
	}

	// Metodo que busca clientes por estado
	public List<Clientes> clientesXEstado(int estado) {

		try {
			session.beginTransaction();
			List<Clientes> cl = session
					.createNativeQuery("select * from clientes where estado='" + estado + "'", Clientes.class)
					.getResultList();
			session.flush();
			session.getTransaction().commit();
			return cl;
		} catch (Exception e) {
			session.clear();
			return null;
		}
	}

	// Metodo que busca clientes por nombre
	public List<Clientes> clientesXEstado(String cliente) {

		try {
			session.beginTransaction();
			List<Clientes> cl = session.createNativeQuery(
					"select * from clientes inner join personas on personas.id_per=clientes.persona where nombre like '"
							+ cliente + "%'",
					Clientes.class).getResultList();
			session.flush();
			session.getTransaction().commit();
			return cl;
		} catch (Exception e) {
			session.flush();
			return null;
		}
	}

	// Metodo que busca por numero de dui y estado del cliente
	public List<Clientes> clientesXEstadoxDui(String dui, int estado) {

		try {
			session.beginTransaction();
			List<Clientes> cl = session.createNativeQuery(
					"select * from clientes inner join personas on personas.id_per=clientes.persona where clientes.estado="
							+ estado + " and dui='" + dui + "'",
					Clientes.class).getResultList();
			session.flush();
			session.getTransaction().commit();
			return cl;
		} catch (Exception e) {
			session.clear();
			return null;
		}
	}
}
