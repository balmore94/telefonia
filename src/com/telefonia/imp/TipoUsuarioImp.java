package com.telefonia.imp;

import java.util.List;


import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.telefonia.models.TipoUsu;
import com.telefonia.utils.AbstractFacade;
import com.telefonia.utils.Dao;
import com.telefonia.utils.HibernateUtil;

@Repository
public class TipoUsuarioImp extends AbstractFacade<TipoUsu> implements Dao<TipoUsu>{
	
	private Session session = HibernateUtil.getSessionFactory().openSession();
	
	public TipoUsuarioImp() {
		super(TipoUsu.class);
	}

	
	//Método que decuelve una lista de tipo usuario
	
	public List<TipoUsu> tipousuario(){
		
		try {
			session.beginTransaction();
			List<TipoUsu> tp = session.createNativeQuery("select * from tipo_usu", TipoUsu.class).getResultList();
			session.flush();
			session.getTransaction();
			return tp;
		} catch (Exception e) {
			return null;
			// TODO: handle exception
		}
	}
}
