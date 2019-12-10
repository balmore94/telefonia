package com.telefonia.imp;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.telefonia.models.Configuracion;
import com.telefonia.utils.AbstractFacade;
import com.telefonia.utils.Dao;
import com.telefonia.utils.HibernateUtil;

@Repository
public class ConfiguracionImp extends AbstractFacade<Configuracion> implements Dao<Configuracion> {

	private Session session = HibernateUtil.getSessionFactory().openSession();
	
	public ConfiguracionImp() {
		super(Configuracion.class);
	}

	/*
	 * @Autowired SessionFactory getConex;
	 */

	

	public Configuracion lisConfId(int d) {
		Configuracion configu = new Configuracion();
		List<Configuracion> lis = new ArrayList<>();
		try {
			session.beginTransaction();
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Configuracion> cq = cb.createQuery(Configuracion.class);
			Root<Configuracion> conf = cq.from(Configuracion.class);
			cq.where(cb.equal(conf.get("idConf"), d));

			lis = session.createQuery(cq).getResultList();
			configu = lis.get(0);
			session.getTransaction().commit();
			// session.close();
			return configu;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}
