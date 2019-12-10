package com.telefonia.controls;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.telefonia.imp.ClienteImp;
import com.telefonia.imp.ContratosImp;
import com.telefonia.imp.UsuarioImp;
import com.telefonia.models.Clientes;
import com.telefonia.models.Contratos;
import com.telefonia.models.Paquetes;
import com.telefonia.models.TipoContrato;
import com.telefonia.models.Usuarios;
import com.telefonia.utils.Dao;

@SuppressWarnings("unused")
@Controller
public class ManejoContrato {

	ModelAndView mav = new ModelAndView();

	@Autowired
	private Dao<Contratos> contrato;

	@Autowired
	private Dao<TipoContrato> tipoContrato;

	@Autowired
	private Dao<Clientes> clientes;

	@Autowired
	private Dao<Usuarios> usuarios;

	@Autowired
	private ClienteImp client;

	@Autowired
	private UsuarioImp usuarioimp;

	@Autowired
	private ContratosImp contratos;

	/** redirige a la vista de registro de un contrato */
	@RequestMapping(value = "/newContrato", method = RequestMethod.GET)
	public ModelAndView newContrato(@RequestParam("pac") int pac, @RequestParam("n") String n) {
		mav.setViewName("registro_contratos");

		//
		List<TipoContrato> tipo_c = tipoContrato.findAll();
		List<Clientes> cliente = clientes.findAll();
		List<Usuarios> usu = usuarios.findAll();
		mav.addObject("tipo_c", tipo_c);
		mav.addObject("cliente", cliente);
		mav.addObject("usuarios", usu);
		mav.addObject("pac", pac);
		mav.addObject("n", n);
		/*
		 * for (int i = 0; i < list.size(); i++) { String ob[]=list.get(i);
		 * System.out.println("xxxxxxxxxxxx llego---> "+ob[0]+" -- "+ob[1]); }
		 */

		return mav;
	}

	/** Retorna la lista de clientes */
	@RequestMapping(value = "/readContratos", method = RequestMethod.GET)
	public ModelAndView readContrato() {
		List<Contratos> listCon = new ArrayList<Contratos>();
		listCon = contrato.findAll();
		mav.addObject("contratos", listCon);
		mav.setViewName("contratos");
		return mav;
	}

	/** Metodo que busca por Estado */
	@RequestMapping(value = "/searchContratoEstado", method = RequestMethod.POST)
	public ModelAndView searchContratoXEstado(@RequestParam("estado") int estado) {
		List<Contratos> listCon = new ArrayList<Contratos>();
		listCon = contratos.contratosXEstado(estado);
		try {

			switch (estado) {
			case 0:

				if (listCon.size() == 0) {
					mav.addObject("msg", "<b style='color: red;'>No se han encrontrado resultados</b>");
					mav.setViewName("contratos");
				} else {

					mav.addObject("msg", "<b style='color: green;'>Contratos Inactivos</b>");
					mav.setViewName("contratos");
				}
				break;

			case 1:
				if (listCon.size() == 0) {
					mav.addObject("msg", "<b style='color: red;'>No se han encrontrado resultados</b>");
					mav.setViewName("contratos");
				} else {

					mav.addObject("msg", "<b style='color: green;'>Contratos Activos</b>");
					mav.setViewName("contratos");
				}
				break;
			}
		} catch (Exception e) {
			mav.addObject("msg", "<b style='color: red;'>Lo siento ha ocurrido un error</b>");
			mav.setViewName("contratos");
		}
		mav.addObject("contratos", listCon);
		return mav;
	}

	/** Metodo que busca por cliente */
	@RequestMapping(value = "/searchContratoXCliente", method = RequestMethod.POST)
	public ModelAndView searchContratoXCliente(@RequestParam("client") String cliente) {
		
		List<Contratos> listCon = new ArrayList<Contratos>();
		try {

			
			listCon = contratos.contratosXClientes(cliente);
			if (listCon.size() == 0) {
				mav.addObject("msg",
						"<b style='color: red;'>No se han encrontrado resultados para " + cliente + "</b>");
				mav.setViewName("contratos");
			} else {
				mav.addObject("contratos", listCon);
				mav.addObject("msg", "<b style='color: green;'>Busquedas que coinciden</b>");
				mav.setViewName("contratos");
			}
		} catch (Exception e) {
			// TODO: handle exception-
		}
		return mav;
	}

	/** Metodo que busca por año */
	@RequestMapping(value = "/searchContratoXanio", method = RequestMethod.POST)
	public ModelAndView searchContratoXanio(@RequestParam("anio") String anio) {
		try {
			List<Contratos> listCon = new ArrayList<Contratos>();

			listCon = contratos.contratosXanio(anio);
			if (listCon.size() == 0) {
				mav.addObject("msg", "<b style='color: red;'> No hay resultados</b>");
				mav.setViewName("contratos");
			} else {
				mav.addObject("contratos", listCon);
				mav.addObject("msg", "<b style='color: green;'>Busquedas que coinciden</b>");
				mav.setViewName("contratos");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return mav;
	}

	/** Metodo para consultar por id de contrato */
	@RequestMapping(value = "/consultarContrato", method = RequestMethod.GET)
	public ModelAndView consultarId(@RequestParam("cod") int idContrato) {
		System.out.println("Llenando el numero " + idContrato);
		Contratos lista = contratos.listConId(idContrato);
		mav.addObject("lista", lista);
		mav.setViewName("editar_contrato");
		return mav;
	}

	/** Metodo para actualizar contrato */
	@RequestMapping(value = "/updateContrato", method = RequestMethod.POST)
	public ModelAndView finContrato(@RequestParam("id") int id, @RequestParam("fec_inicio") String fec_inicio,
			@RequestParam("fec_fin") String fec_fin, @RequestParam("tipo_c") int tipo_c,
			@RequestParam("cliente") int cliente, @RequestParam("usuarios") int usuarios) throws ParseException {

		try {
			TipoContrato tc = new TipoContrato();
			tc.setIdTpcontrat(tipo_c);

			Clientes ci = new Clientes();
			ci.setIdCliente(cliente);

			Usuarios us = new Usuarios();
			us.setIdUsu(usuarios);

			Contratos c = new Contratos();

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = dateFormat.parse(fec_inicio);

			DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
			Date date1 = dateFormat.parse(fec_fin);

			c.setIdContrat(id);
			c.setFecInicio(date);
			c.setFecFin(date1);
			c.setTipoContrato(tc);
			c.setClientes(ci);
			c.setEstado(false);
			c.setUsuarios(us);
			contrato.update(c);
			mav.addObject("mens", "Exito Contrato Finalizado");
		} catch (Exception e) {
			mav.addObject("mens", "Ha Ocurrido Un Error " + e.getMessage());
		}
		mav.setViewName("editar_contrato");
		readContrato();
		return mav;
	}
}
