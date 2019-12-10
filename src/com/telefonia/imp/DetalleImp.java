package com.telefonia.imp;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Join;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.telefonia.models.Descripcion;
import com.telefonia.models.Detalle;
import com.telefonia.models.Facturas;
import com.telefonia.utils.AbstractFacade;
import com.telefonia.utils.Dao;
import com.telefonia.utils.HibernateUtil;

@Repository
public class DetalleImp extends AbstractFacade<Detalle> implements Dao<Detalle> {

	private Session session = HibernateUtil.getSessionFactory().openSession();

	public DetalleImp() {
		super(Detalle.class);
	}

	public List<Detalle> listadoPorFac(int f) {

		List<Detalle> listaD = new ArrayList<>();
		try {
			session.beginTransaction();
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Detalle> cq = cb.createQuery(Detalle.class);
			Root<Detalle> det = cq.from(Detalle.class);
			Join<Detalle, Facturas> df=det.join("facturas");
			Join<Detalle, Descripcion> dd=det.join("descripcion");
			cq.where(cb.equal(det.get("facturas"),f),cb.equal(dd.get("estado"),1));
			listaD=session.createQuery(cq).getResultList();
			System.out.println("Datos obtenidos ----------------------> "+listaD.size());
			return listaD;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
}
