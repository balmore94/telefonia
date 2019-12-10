package com.telefonia.imp;



import org.springframework.stereotype.Repository;

import com.telefonia.models.Departamentos;
import com.telefonia.utils.AbstractFacade;
import com.telefonia.utils.Dao;

@Repository
public class DepartamentosImp extends AbstractFacade<Departamentos> implements Dao<Departamentos> {

	public DepartamentosImp() {
		super(Departamentos.class);
	}
}
