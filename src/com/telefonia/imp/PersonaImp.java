package com.telefonia.imp;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.telefonia.models.Personas;
import com.telefonia.utils.AbstractFacade;
import com.telefonia.utils.Dao;
import com.telefonia.utils.HibernateUtil;

@Repository
public class PersonaImp extends AbstractFacade<Personas> implements Dao<Personas> {

	private Session session = HibernateUtil.getSessionFactory().openSession();

	public PersonaImp() {
		super(Personas.class);
	}

	// Método que devuelve las personas de tipo usuario
	public List<Personas> personasUsuario(){
		
		try {
			session.beginTransaction();
			List<Personas> per = session.createNativeQuery(
					"select * from personas where tipo='0' and estado='a'", Personas.class).getResultList();
					session.flush();
			session.getTransaction();
			return per;
		} catch (Exception e) {
			session.clear();
			return null;
		}
	}
}
