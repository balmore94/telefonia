package com.telefonia.imp;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.telefonia.models.Descripcion;
import com.telefonia.utils.AbstractFacade;
import com.telefonia.utils.Dao;
import com.telefonia.utils.HibernateUtil;

@Repository
public class DescripcionImp extends AbstractFacade<Descripcion> implements Dao<Descripcion> {
	private Session session = HibernateUtil.getSessionFactory().openSession();

	public DescripcionImp() {
		super(Descripcion.class);
		// TODO Auto-generated constructor stub
	}

	public List<Descripcion> listadoDes(int id) {

		List<Descripcion> listF = new ArrayList<>();
		try {
			session.beginTransaction();
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Descripcion> cq = cb.createQuery(Descripcion.class);
			Root<Descripcion> fac = cq.from(Descripcion.class);
			cq.where(cb.equal(fac.get("contratos"), id));
			listF = session.createQuery(cq).getResultList();

			// f = listF.get(0);
			session.getTransaction().commit();
			// session.close();
			return listF;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/*Metodo que retorna una lista con todas las descripciones de contrato*/
	public List<Descripcion> descripciones(){
		try {
			session.beginTransaction();
			List<Descripcion> listadesxfac = session.createNativeQuery(
					"SELECT descripcion.* FROM descripcion, facturas WHERE facturas.contrato = descripcion.contrato",
					Descripcion.class).getResultList();
			session.flush();
			session.getTransaction().commit();
			return listadesxfac;
		} catch (Exception e) {
			return null;
		}
	}
	
}
