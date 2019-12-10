package com.telefonia.imp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.telefonia.models.Clientes;
import com.telefonia.models.Contratos;
import com.telefonia.models.Descripcion;
import com.telefonia.models.Detalle;
import com.telefonia.models.Facturas;
import com.telefonia.models.Servicios;
import com.telefonia.utils.AbstractFacade;
import com.telefonia.utils.Dao;
import com.telefonia.utils.HibernateUtil;

@Repository
public class FacturasImp extends AbstractFacade<Facturas> implements Dao<Facturas> {

	private Session session = HibernateUtil.getSessionFactory().openSession();

	public FacturasImp() {
		super(Facturas.class);
	}

	public Facturas busquedaN(int n) {
		Facturas f = new Facturas();
		List<Facturas> listF = new ArrayList<>();
		try {
			session.beginTransaction();
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Facturas> cq = cb.createQuery(Facturas.class);
			Root<Facturas> fac = cq.from(Facturas.class);
			cq.where(cb.equal(fac.get("idFac"), n),cb.equal(fac.get("estado"),1));
			listF = session.createQuery(cq).getResultList();

			if (listF.size() > 0) {
				f = listF.get(0);
			} else {
				f.setIdFac(0);
			}

			// f = listF.get(0);
			session.getTransaction().commit();
			// session.close();
			return f;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/* Metodo para obtener los clientes para factura */
	public List<Clientes> obtenerClientes() {
		try {
			session.beginTransaction();
			List<Clientes> cli = session.createNativeQuery(
					"SELECT clientes.id_cliente, clientes.codigo_cl, personas.nombre, personas.apellido FROM contratos INNER JOIN clientes ON contratos.cliente = clientes.id_cliente INNER JOIN personas ON clientes.persona = personas.id_per WHERE contratos.estado = 1 AND contratos.estado = 1;",
					Clientes.class).getResultList();
			session.flush();
			session.getTransaction().commit();
			return cli;
		} catch (Exception e) {
			session.clear();
			return null;
		}
	}

	/// ----------------------Metodo de Busqueda Hecho por "2222"
	public List<Object[]> obtenerDetalle(List<Detalle> lis) {
		List<Object[]> listado = new ArrayList<>();
		try {

			for (int i = 0; i < lis.size(); i++) {

				session.beginTransaction();
				String sql = "SELECT servicios.id_servicio, servicios.nombre, descripcion.costo, (select SUM(descripcion.costo) from descripcion,contratos where descripcion.contrato = contratos.id_contrat ) 'total' FROM descripcion INNER JOIN servicios ON descripcion.servicio = servicios.id_servicio INNER JOIN contratos ON descripcion.contrato = contratos.id_contrat where contratos.id_contrat="
						+ lis.get(i).getDescripcion().getIdDesc() + ";";

				SQLQuery query = session.createSQLQuery(sql);
				List<Object[]> rows = query.list();
				listado.add(rows.get(0));
			}
			session.getTransaction().commit();
			return listado;
		} catch (Exception e) {
			session.clear();
			return null;
		}
	}
	/// ----------------------

	/* Metodo para obtener detalles de la factura */
	public List<Object[]> obtenerDetalles(List<Contratos> list) {
		List<Object[]> lista = new ArrayList<>();
		try {
			
			for(int i = 0; i < list.size(); i++) {
				session.beginTransaction();
				String sql = "SELECT servicios.id_servicio, servicios.nombre, descripcion.costo, descripcion.contrato, (select SUM(descripcion.costo) from descripcion,contratos where descripcion.contrato = contratos.id_contrat ) 'total' FROM descripcion INNER JOIN servicios ON descripcion.servicio = servicios.id_servicio INNER JOIN contratos ON descripcion.contrato = contratos.id_contrat where contratos.id_contrat=" + list.get(i).getIdContrat() + ";";
				SQLQuery query = session.createSQLQuery(sql);
				List<Object[]> registros = query.list();
				lista.add(registros.get(0));
			}
			session.getTransaction();
			return lista;

		} catch (Exception e) {
			session.clear();
			return null;
		}
	}

	/* Calculo de consumo extra */
	public List<Servicios> cargoExtra() {
		try {
			session.beginTransaction();
			List<Servicios> extra = session.createNativeQuery(
					"SELECT servicios.id_servicio, servicios.nombre, descripcion.costo, (select descripcion.costo*configuracion.costo  from descripcion, servicios, configuracion where servicios.id_servicio = descripcion.servicio) as 'Extra' FROM descripcion INNER JOIN servicios ON descripcion.servicio = servicios.id_servicio INNER JOIN contratos ON descripcion.contrato = contratos.id_contrat",
					Servicios.class).getResultList();
			session.flush();
			session.getTransaction().commit();
			return extra;
		} catch (Exception e) {
			return null;
		}
	}

	public double[] calcular(int d) {
		double da[] = new double[2];
		Facturas f = busquedaN(d);
		int dias = 0;
		int horas = 0;
		int minutos = 0;
		Date fechaFinal = new Date();
		Date fechaInicial = f.getFechLimite();
		int diferencia = (int) ((fechaFinal.getTime() - fechaInicial.getTime()) / 1000);

		if (diferencia > 86400) {
			dias = (int) Math.floor(diferencia / 86400);
			diferencia = diferencia - (dias * 86400);
		}
		if (diferencia > 3600) {
			horas = (int) Math.floor(diferencia / 3600);
			diferencia = diferencia - (horas * 3600);
		}
		if (diferencia > 60) {
			minutos = (int) Math.floor(diferencia / 60);
			diferencia = diferencia - (minutos * 60);
		}
		/*
		 * System.out.println("Hay " + dias + " dias, " + horas + " horas, " + minutos +
		 * " minutos y " + diferencia + " segundos de diferencia");
		 */
		double ca = dias * 6.50;
		da[0] = ca;
		da[1] = dias;
		return da;
	}

	public void pagar(int d) {
		String query = "Update facturas set estado=0 where id_fac="+d+";";
		System.out.println("Consulta -->" + query);
		try {
			Transaction ta = session.beginTransaction();
			Query qr = session.createNativeQuery(query);
			qr.executeUpdate();
			ta.commit();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Facturas> facturasFecha() {
		Date fecha = new Date();		
		SimpleDateFormat formateador = new SimpleDateFormat("yy/MM/dd");
		session.beginTransaction();
		try {
			List<Facturas> lisFa = session.createNativeQuery(
					"SELECT * FROM facturas WHERE fech_emision = '"+formateador.format(fecha)+"';", Facturas.class).getResultList();
			System.out.print("SELECT * FROM facturas WHERE fecha_emision = " +formateador.format(fecha)+ ";");
			session.flush();
			session.getTransaction().commit();
			return lisFa;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
