package com.telefonia.imp;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.telefonia.models.Contratos;
import com.telefonia.utils.AbstractFacade;
import com.telefonia.utils.Dao;
import com.telefonia.utils.HibernateUtil;

@Repository
public class ContratosImp extends AbstractFacade<Contratos> implements Dao<Contratos> {

	private Session session = HibernateUtil.getSessionFactory().openSession();

	public ContratosImp() {
		super(Contratos.class);
	}

	/** Metodo para consultar contratos por id */
	public Contratos listConId(int d) {
		Contratos contra = new Contratos();
		List<Contratos> lis = new ArrayList<>();

		try {
			session.beginTransaction();

			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Contratos> cq = cb.createQuery(Contratos.class);
			Root<Contratos> cont = cq.from(Contratos.class);
			cq.where(cb.equal(cont.get("idContrat"), d));

			lis = session.createQuery(cq).getResultList();
			contra = lis.get(0);
			session.getTransaction().commit();
			return contra;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/** Metodo que busca contratos por estado */
	public List<Contratos> contratosXEstado(int estado) {

		List<Contratos> con = new ArrayList<Contratos>();
		try {
			session.beginTransaction();
			con = session.createNativeQuery("select * from contratos where estado='" + estado + "'", Contratos.class)
					.getResultList();
			session.flush();
			session.getTransaction().commit();
			return con;
		} catch (Exception e) {
			session.clear();
			return null;
		}
	}

	/** Metodo que busca contratos por clientes */
	public List<Contratos> contratosXClientes(String cliente) {

		List<Contratos> con = new ArrayList<Contratos>();
		try {
			session.beginTransaction();
			con = session.createNativeQuery(
					"select * from contratos inner join clientes on clientes.id_cliente = contratos.cliente inner join personas on personas.id_per= clientes.persona where personas.nombre like '"
							+ cliente + "_%'",
					Contratos.class).getResultList();
			session.flush();
			session.getTransaction().commit();
			return con;
		} catch (Exception e) {
			session.flush();
			return null;
		}
	}

	/** Metodo que busca por meses */
	public List<Contratos> contratosXMes(String fecha, String fecha_fin) {

		List<Contratos> con = new ArrayList<Contratos>();
		try {
			session.beginTransaction();
			con = session.createNativeQuery(
					"select * from contratos where date(fec_inicio) between '" + fecha + "' and '" + fecha_fin + "'",
					Contratos.class).getResultList();
			session.flush();
			session.getTransaction().commit();

			System.out.println(con.size());
			return con;
		} catch (Exception e) {
			session.flush();
			return null;
		}
	}

	/** Metodo que busca por año */
	public List<Contratos> contratosXanio(String anio) {

		List<Contratos> con = new ArrayList<Contratos>();
		try {
			session.beginTransaction();
			con = session.createNativeQuery("select * from contratos where date(fec_inicio) between '" + anio
					+ "-01-01' and '" + anio + "-12-31'", Contratos.class).getResultList();
			session.flush();
			session.getTransaction().commit();

			System.out.println(con.size());
			return con;
		} catch (Exception e) {
			session.flush();
			return null;
		}
	}

}
