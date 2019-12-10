package com.telefonia.controls;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.telefonia.imp.ClienteImp;
import com.telefonia.imp.ConsumoExImp;
import com.telefonia.imp.ContratosImp;
import com.telefonia.imp.ControlSerImp;
import com.telefonia.imp.DescripcionImp;
import com.telefonia.imp.UsuarioImp;
import com.telefonia.models.Clientes;
import com.telefonia.models.Contratos;
import com.telefonia.models.CtrlServ;
import com.telefonia.models.Descripcion;
import com.telefonia.models.Detalle;
import com.telefonia.models.Paquetes;
import com.telefonia.models.Servicios;
import com.telefonia.models.TipoContrato;
import com.telefonia.models.Usuarios;
import com.telefonia.utils.Dao;

@SuppressWarnings("unused")
@Controller
public class DescripcionManejo {
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
	// ---
	@Autowired
	private DescripcionImp descripImp;
	// ---
	// --
	@Autowired
	private ControlSerImp crs;
	// --
	private List<String[]> list;
	Paquetes paqc;
	// ----
	@Autowired
	private ConsumoExImp cextra;
	// ----
	/** Registra un nuevo contrato */
	@RequestMapping(value = "/newContrato", method = RequestMethod.POST)
	public ModelAndView createContrato(@RequestParam("fec_inicio") String fec_inicio,
			@RequestParam("fec_fin") String fec_fin, @RequestParam("tipo_c") int tipo_c,
			@RequestParam("cliente") int cliente, @RequestParam("usuarios") int usuarios, @RequestParam("pac") int pac)
			throws ParseException {
		// ---
		paqc = new Paquetes();
		list = new ArrayList<>();
		list = paqc.llenar(pac);
		System.out.println("datos " + list.size());
		// ---
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
			c.setFecInicio(date);
			c.setFecFin(date1);
			c.setTipoContrato(tc);
			c.setClientes(ci);
			c.setEstado(true);
			c.setUsuarios(us);
			contrato.save(c);

			for (int i = 0; i < list.size(); i++) {
				crs = new ControlSerImp();
				descripImp = new DescripcionImp();
				String ob[] = list.get(i);
				Descripcion descrip = new Descripcion();
				descrip.setContratos(c);
				BigDecimal costo = new BigDecimal(ob[1]);
				Servicios ser = new Servicios();
				ser.setIdServicio(Integer.parseInt(ob[0]));
				descrip.setCosto(costo);
				descrip.setVigencia(Integer.parseInt(ob[2]));
				descrip.setServicios(ser);
				descrip.setEstado(true);
				descripImp.save(descrip);
				/* evaluo para guardar en control de servicio */
				CtrlServ control = new CtrlServ();
				control.setConsumido(0);
				if (Integer.parseInt(ob[0]) == 1) {
					control.setDisponible(Integer.parseInt(ob[6]));
				} else if (Integer.parseInt(ob[0]) == 2) {
					control.setDisponible(Integer.parseInt(ob[4]));
				} else if (Integer.parseInt(ob[0]) == 4) {
					control.setDisponible(Integer.parseInt(ob[5]));
				}
				control.setDescripcion(descrip);
				crs.save(control);

			}
			mav.addObject("respuesta", "Exito Contrato Registrado");
		} catch (Exception e) {
			mav.addObject("respuesta", "Ha Ocurrido Un Error " + e.getMessage());
		}
		mav.setViewName("inicio");
		// newContrato();
		return mav;
	}

	@GetMapping("/info")
	public String userInfo(@SessionAttribute("userLoggin") Usuarios user) {

		System.out.println("Email: " + user.getUsuario());

		return "inicio";
	}

}
