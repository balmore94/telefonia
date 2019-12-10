package com.telefonia.imp;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.telefonia.models.Configuracion;
import com.telefonia.models.ConsExtra;
import com.telefonia.models.CtrlServ;
import com.telefonia.models.Descripcion;
import com.telefonia.models.Detalle;
import com.telefonia.models.Facturas;
import com.telefonia.utils.AbstractFacade;
import com.telefonia.utils.Dao;
import com.telefonia.utils.HibernateUtil;

@Repository
public class ConsumoExImp extends AbstractFacade<ConsExtra> implements Dao<ConsExtra> {
	private Session session = HibernateUtil.getSessionFactory().openSession();

	public ConsumoExImp() {
		super(ConsExtra.class);
		// TODO Auto-generated constructor stub
	}

	// --Agrago un llamdo a ConfImp para obtener los cobros extras
	ConfiguracionImp confIMP = new ConfiguracionImp();
	Configuracion conf;

	// ---
	List<CtrlServ> listaD;
	BigDecimal x=new BigDecimal(0);

	// creo un metodo general que capture lista descripcion
	public double calculos(List<Descripcion> la) {
		for (int i = 0; i < la.size(); i++) {
			consultar(la.get(i).getIdDesc());
			consultarEx();
		}
		x=x.setScale(2,RoundingMode.HALF_UP);
		return x.doubleValue();
	}

	// creo un metodo general que capture lista detalle
	public double calculosCD(List<Detalle> la) {
		for (int i = 0; i < la.size(); i++) {
			consultar(la.get(i).getDescripcion().getIdDesc());
			consultarEx();
		}
		x=x.setScale(2,RoundingMode.HALF_UP);
		return x.doubleValue();
	}

	// --
	public void consultar(int s) {
		listaD = new ArrayList<>();
		try {
			session.beginTransaction();
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<CtrlServ> cq = cb.createQuery(CtrlServ.class);
			Root<CtrlServ> ctr = cq.from(CtrlServ.class);
			cq.where(cb.equal(ctr.get("descripcion"), s), cb.equal(ctr.get("disponible"), ctr.get("consumido")));
			listaD = session.createQuery(cq).getResultList();
			session.getTransaction().commit();
			// System.out.println("metod de optencio de datos -------------------> " +
			// listaD.size());
			// return listaD;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			// return null;
		}
	}

	public void consultarEx() {
		// Obtengo con el for la posicion del arreglo para obtener los consumos extras
		// luego guardare eso en un arrglo ya con sus calculos
		try {
			List<ConsExtra> lis;
			ConsExtra conX;
			// creo un objeto de consumo extra para usarlo como identificador cada ves que
			// entre al ciclo
			for (int i = 0; i < listaD.size(); i++) {
				lis = new ArrayList<>();
				session.beginTransaction();
				CriteriaBuilder cb = session.getCriteriaBuilder();
				CriteriaQuery<ConsExtra> cq = cb.createQuery(ConsExtra.class);
				Root<ConsExtra> ctr = cq.from(ConsExtra.class);
				cq.where(cb.equal(ctr.get("descripcion"), listaD.get(i).getDescripcion()));
				lis = session.createQuery(cq).getResultList();
				conX = new ConsExtra();
				conX = lis.get(0);
				// obtener el id del servicio para ocuparlos en la tabla config;
				int s = conX.getDescripcion().getServicios().getIdServicio();
				BigDecimal conE = conX.getConsumo();
				System.out.println("Resultado------------->  "+conE);
				if (s == 1) {
					conf = new Configuracion();
					conf = confIMP.lisConfId(2);
					 x=x.add(conf.getTafifaNormal().multiply(conE));
					 System.out.println("Datos de primera condicion----------->"+x);
				} else if (s == 2) {
					conf = new Configuracion();
					conf = confIMP.lisConfId(1);
					double d=conf.getCosto()*conE.doubleValue();
					BigDecimal co=new BigDecimal(d);
					x= x.add(co);
					 System.out.println("Datos de primera segunda----------->"+x);
				} else if (s == 4) {
					conf = new Configuracion();
					conf = confIMP.lisConfId(3);
					x=x.add(conf.getTafifaNormal().multiply(conE));
					 System.out.println("Datos de primera tercera----------->"+x);
				}
				session.getTransaction().commit();
			}

			// return listaD;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			// return null;
		}
	}

}
