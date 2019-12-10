package com.telefonia.imp;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.telefonia.models.CtrlServ;
import com.telefonia.utils.AbstractFacade;
import com.telefonia.utils.Dao;
import com.telefonia.utils.HibernateUtil;

@Repository
public class ControlSerImp extends AbstractFacade<CtrlServ> implements Dao<CtrlServ> {

	private Session session = HibernateUtil.getSessionFactory().openSession();

	public ControlSerImp() {
		super(CtrlServ.class);
		// TODO Auto-generated constructor stub
	}

}
