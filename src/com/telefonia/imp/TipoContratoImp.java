package com.telefonia.imp;

import org.springframework.stereotype.Repository;

import com.telefonia.models.TipoContrato;
import com.telefonia.utils.AbstractFacade;
import com.telefonia.utils.Dao;

@Repository
public class TipoContratoImp extends AbstractFacade<TipoContrato> implements Dao<TipoContrato> {

	public TipoContratoImp() {
		super(TipoContrato.class);
	}

}
