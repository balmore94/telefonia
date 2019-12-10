package com.telefonia.imp;

import com.telefonia.models.Municipios;
import com.telefonia.utils.AbstractFacade;
import com.telefonia.utils.Dao;

public class MunicipiosImp extends AbstractFacade<Municipios> implements Dao<Municipios> {

	public MunicipiosImp() {
		super(Municipios.class);
	}

}
