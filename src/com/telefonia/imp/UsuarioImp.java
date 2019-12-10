package com.telefonia.imp;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.telefonia.models.Usuarios;
import com.telefonia.utils.AbstractFacade;
import com.telefonia.utils.Dao;
import com.telefonia.utils.HibernateUtil;

@Repository
public class UsuarioImp extends AbstractFacade<Usuarios> implements Dao<Usuarios> {

	private Session session = HibernateUtil.getSessionFactory().openSession();

	public UsuarioImp() {
		super(Usuarios.class);
	}

	// Metodo de inicio de session vefifica usuario y contraseña y el estado
	@SuppressWarnings("unchecked")
	public Usuarios login(Usuarios user, String pass) {

		Transaction t = session.beginTransaction();
		try {
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Usuarios> cq = cb.createQuery(Usuarios.class);
			Root<Usuarios> root = cq.from(Usuarios.class);

			String query = "select * from usuarios where usuario=? and pass=aes_encrypt(?,'321')";
			Query q = session.createNativeQuery(query);
			q.setParameter(1, user.getUsuario());
			q.setParameter(2, pass);

			List<Usuarios> lista = q.getResultList();
			if (lista.size() > 0) {
				cq.where(cb.equal(root.get("usuario"), user.getUsuario()), cb.and(cb.equal(root.get("estado"), true)));
				user = session.createQuery(cq).uniqueResult();
			} else {
				user = null;
			}
		} catch (Exception e) {
			session.flush();
			return null;
		} finally {
			session.flush();
		}
		session.flush();
		t.commit();
		return user;

	}

	// Metodo que registra un nuevo usuario
	public void inserU(Usuarios us, String pass) {
		String query = "insert into usuarios(id_usu,usuario,pass,persona,estado,tipo_u) values(" + 0 + ",'"
				+ us.getUsuario() + "',AES_ENCRYPT('" + pass + "','321')," + us.getPersonas().getIdPer() + "," + 1 + ","
				+ us.getTipoUsu().getIdTpu() + ");";
		System.out.println("Consulta -->" + query);
		try {
			Transaction t = session.beginTransaction();
			Query q = session.createNativeQuery(query);
			q.executeUpdate();
			t.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//
//	//Metodo que retorna una lista de	todos los usuarios
//
//
////	Metodo que retorna una lista de todos los usuarios
//	public Usuarios listaUsuario(int id_u) {
//		try {
//			Transaction t = session.beginTransaction();	
//			CriteriaBuilder cb = session.getCriteriaBuilder();
//			CriteriaQuery<Usuarios> cq = cb.createQuery(Usuarios.class);
//			Root<Usuarios> root = cq.from(Usuarios.class);
//			cq.where(cb.equal(root.get("idUsu"), id_u), cb.equal(root.get("estado"), true));		
//			Usuarios u=new Usuarios();
//			u=session.createQuery(cq).uniqueResult();
//			t.commit();
//			return u;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
}
