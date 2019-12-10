package com.telefonia.imp;

import org.springframework.stereotype.Repository;

import com.telefonia.models.Direcciones;
import com.telefonia.utils.AbstractFacade;
import com.telefonia.utils.Dao;

@Repository
public class DireccionesImp extends AbstractFacade<Direcciones> implements Dao<Direcciones> {

	public DireccionesImp() {
		super(Direcciones.class);
	}

}
