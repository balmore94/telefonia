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
import com.telefonia.imp.TipoUsuarioImp;
import com.telefonia.imp.UsuarioImp;
import com.telefonia.models.Clientes;
import com.telefonia.models.Direcciones;
import com.telefonia.models.Municipios;
import com.telefonia.models.Personas;
import com.telefonia.models.TipoUsu;
import com.telefonia.models.Usuarios;
import com.telefonia.utils.Dao;

@Controller
public class PersonaCliente {
	ModelAndView mav = new ModelAndView();

	@Autowired
	private Dao<Clientes> clientes;

	@Autowired
	private Dao<Municipios> municipios;

	@Autowired
	private ClienteImp client;

	@Autowired
	private Dao<Direcciones> direcciones;

	@Autowired
	private Dao<Personas> personas;

	@Autowired
	private UsuarioImp usuarioimp;

	@Autowired
	private TipoUsuarioImp tipou;

	// Redirige a la vista de registro de un cliente
	@RequestMapping(value = "/newCliente", method = RequestMethod.GET)
	public ModelAndView newPersona() {
		mav.addObject(new Clientes());

		int i = client.codigoCliente();
		List<Municipios> mun = municipios.findAll();
		mav.addObject("codigo", i);
		mav.addObject("mun", mun);
		mav.setViewName("personas");
		return mav;
	}

	// Retorna la lista de clientes
	@RequestMapping(value = "/readClientes")
	public ModelAndView readCliente() {
		List<Clientes> listC = clientes.findAll();
		mav.addObject("clientes", listC);
		mav.setViewName("clientes");
		return mav;
	}

	// Registra un nuevo cliente
	@RequestMapping(value = "/newCliente", method = RequestMethod.POST)
	public ModelAndView createPersona(@RequestParam("nombre") String nombre, @RequestParam("apellido") String apellido,
			@RequestParam("dui") String dui, @RequestParam("correo") String correo,
			@RequestParam("direccion") String direccion, @RequestParam("municipio") int municipio,
			@RequestParam("codigo") int codigo, @RequestParam("telefono") String telefono,
			@RequestParam("sexo") String sexo, @RequestParam("fNacimiento") String nacimiento) throws ParseException {

		try {

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = dateFormat.parse(nacimiento);

			String revDui = "";

			List<Personas> list = personas.findAll();
			for (Personas per : list) {

				if (!dui.equals(per.getDui())) {

				} else {
					revDui = per.getDui();
				}
			}
			if (!revDui.equals(dui)) {

				Municipios m = new Municipios();
				m.setIdMuni(municipio);

				Direcciones d = new Direcciones();
				d.setDireccion(direccion);
				d.setMunicipios(m);
				direcciones.save(d);

				Personas p = new Personas();
				p.setNombre(nombre);
				p.setApellido(apellido);
				p.setDui(dui);
				p.setSexo(sexo);
				p.setCorreo(correo);
				p.setEstado('1');
				p.setDirecciones(d);
				p.setTelefono(telefono);

				p.setFNacimiento(date);
				p.setTipo(true);
				personas.save(p);

				Clientes c = new Clientes();
				c.setCodigoCl(codigo);
				c.setEstado(true);
				c.setPersonas(p);
				clientes.save(c);
				mav.addObject("text", "<b style='color: Green;'>Exito Cliente Registrado</br>");
			} else {
				mav.addObject("text", "<b style='color: red;'>Error Numero de dui duplicado</b>");
			}
			list = new ArrayList<>();
		} catch (Exception e) {
			mav.addObject("text", "<b style='color: red;'>Error inesperado</b>");
		}
		mav.setViewName("personas");
		newPersona();
		return mav;
	}

	// Recibe la respuesta del get
	@RequestMapping(value = "/searchCliente", method = RequestMethod.GET)
	public ModelAndView searchClient() {

		List<Clientes> listC = new ArrayList<Clientes>();
		listC = clientes.findAll();
		mav.addObject("clientes", listC);
		mav.setViewName("clientes");
		return mav;
	}

	// Busca los clientes por numero de dui
	@RequestMapping(value = "/searchCliente", method = RequestMethod.POST)
	public ModelAndView searchCliente(@RequestParam("ndui") String dui) {

		try {
			List<Clientes> listC = new ArrayList<Clientes>();
			listC = client.clientesXDui(dui);

			if (listC.size() == 0) {
				mav.addObject("msg", "<b style='color: red;'>No se han encrontrado resultados</b>");
				mav.setViewName("clientes");
			} else {
				mav.addObject("clientes", listC);
				mav.addObject("msg", "<b style='color: green;'>Busquedas que coinciden</b>");
				mav.setViewName("clientes");
			}

		} catch (Exception e) {

		}
		return mav;
	}

	// Metodo que busca por Estado
	@RequestMapping(value = "/searchClienteEstado", method = RequestMethod.POST)
	public ModelAndView searchClienteXEstado(@RequestParam("estado") int estado) {
		try {

			List<Clientes> listC = new ArrayList<Clientes>();
			listC = client.clientesXEstado(estado);

			switch (estado) {
			case 0:

				if (listC.size() == 0) {
					mav.addObject("msg", "<b style='color: red;'>No se han encrontrado resultados</b>");
					mav.setViewName("clientes");
				} else {
					mav.addObject("clientes", listC);
					mav.addObject("msg", "<b style='color: green;'>Clientes Inactivos</b>");
					mav.setViewName("clientes");
				}
				break;

			case 1:
				if (listC.size() == 0) {
					mav.addObject("msg", "<b style='color: red;'>No se han encrontrado resultados</b>");
					mav.setViewName("clientes");
				} else {

					mav.addObject("clientes", listC);
					mav.addObject("msg", "<b style='color: green;'>Clientes Activos</b>");
					mav.setViewName("clientes");
				}
				break;
			default:
				mav.addObject("msg", "<b style='color: red;'>Lo siento ha ocurrido un error</b>");
				mav.setViewName("clientes");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return mav;
	}

	// Metodo que busca por cliente
	@RequestMapping(value = "/searchClienteNombre", method = RequestMethod.POST)
	public ModelAndView searchClienteXNombre(@RequestParam("client") String cliente) {
		try {

			List<Clientes> listC = new ArrayList<Clientes>();
			listC = client.clientesXEstado(cliente);
			if (listC.size() == 0) {
				mav.addObject("msg", "<b style='color: red;'>No se han encrontrado resultados par " + cliente + "</b>");
				mav.setViewName("clientes");
			} else {
				mav.addObject("clientes", listC);
				mav.addObject("msg", "<b style='color: green;'>Busquedas que coinciden</b>");
				mav.setViewName("clientes");
			}
		} catch (Exception e) {
			// TODO: handle exception-
		}
		return mav;
	}

	// Metodo que busca un cliente por numero de dui y estado

	@RequestMapping(value = "/searchClienteDuiEstado", method = RequestMethod.POST)
	public ModelAndView searchClienteXDuiEstado(@RequestParam("ndui") String dui, @RequestParam("estado") int estado) {
		try {

			List<Clientes> listC = new ArrayList<Clientes>();
			listC = client.clientesXEstadoxDui(dui, estado);
			if (listC.size() == 0) {
				mav.addObject("msg", "<b style='color: red;'>No se han encrontrado resultados</b>");
				mav.setViewName("clientes");
			} else {
				mav.addObject("clientes", listC);
				mav.addObject("msg", "<b style='color: green;'>Busqueda que coinciden</b>");
				mav.setViewName("clientes");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return mav;
	}

	// Mï¿½todo para ingresar usuarios
	@RequestMapping(value = "/newUsuario", method = RequestMethod.POST)
	public ModelAndView createUsuario(@RequestParam("usuario") String usuario, @RequestParam("pass") String pass,

			@RequestParam("tipo_u") int tipo_u, @RequestParam("nombre") String nombre,
			@RequestParam("apellido") String apellido, @RequestParam("dui") String dui,
			@RequestParam("correo") String correo, @RequestParam("direccion") String direccion,
			@RequestParam("municipio") int municipio, @RequestParam("telefono") String telefono,
			@RequestParam("sexo") String sexo, @RequestParam("fNacimiento") String nacimiento) throws ParseException {
		try {

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = dateFormat.parse(nacimiento);

			Municipios m = new Municipios();
			m.setIdMuni(municipio);

			Direcciones d = new Direcciones();
			d.setDireccion(direccion);
			d.setMunicipios(m);
			direcciones.save(d);

			Personas per = new Personas();
			per.setNombre(nombre);
			per.setApellido(apellido);
			per.setDui(dui);
			per.setSexo(sexo);
			per.setCorreo(correo);
			per.setEstado('1');
			per.setDirecciones(d);
			per.setTelefono(telefono);
			per.setFNacimiento(date);
			per.setTipo(false);
			personas.save(per);

			/// Tipo de usuario
			TipoUsu tpu = new TipoUsu();
			tpu.setIdTpu(tipo_u);
			/// fin de tipo de usuario
			Usuarios u = new Usuarios();
			u.setUsuario(usuario);
			// u.setPass(pass);
			u.setPersonas(per);
			// estado esta true por defecto
			u.setEstado(true);
			u.setTipoUsu(tpu);

			usuarioimp.inserU(u, pass);
			;
			mav.addObject("msg", "Exito Usuario Registrado");
		} catch (Exception e) {
			mav.addObject("msg", "Ha ocurrido un error");
		}
		mav.setViewName("usuarios");
		return mav;
	}

	// Recibe la respuesta del get
	@RequestMapping(value = "/searchUsuario", method = RequestMethod.GET)
	public ModelAndView searchUsuario() {

		List<Usuarios> listUs = new ArrayList<Usuarios>();
		listUs = usuarioimp.findAll();
		mav.addObject("usuarios", listUs);
		mav.setViewName("usuarios");
		return mav;
	}

	// Lista de tipo de usuarios
	@RequestMapping(value = "/registroUsuario", method = RequestMethod.GET)
	public ModelAndView vistaUsuario() {

		List<TipoUsu> listTP = new ArrayList<TipoUsu>();
		listTP = tipou.findAll();
		mav.setViewName("usuarios");
		mav.addObject("listTP", listTP);
		List<Municipios> mun = municipios.findAll();
		mav.addObject("mun", mun);
		return mav;
	}

	// Retorna la lista de usuario
	@RequestMapping(value = "/readUsuarios")
	public ModelAndView readUsuarios() {
		List<Usuarios> listUs = usuarioimp.findAll();
		mav.addObject("usuarios", listUs);
		mav.setViewName("usuarios");
		return mav;
	}

	// metodo para seleccionar un cliente
	@RequestMapping(value = "/editCliente", method = RequestMethod.GET)
	public ModelAndView editCliente(@RequestParam("t") int id) {

		Clientes cl = clientes.readById(id);
		List<Municipios> mun = municipios.findAll();
		mav.addObject("mun", mun);
		mav.addObject("cliente", cl);
		mav.setViewName("clienteEdit");
		return mav;
	}

	// metodo para Actualizar registro un cliente
	@RequestMapping(value = "/updateClient", method = RequestMethod.POST)
	public ModelAndView updateCliente(@RequestParam("idDir") int idDir, @RequestParam("idCliente") int idCliente,
			@RequestParam("idPer") int idPer, @RequestParam("nombre") String nombre,
			@RequestParam("apellido") String apellido, @RequestParam("dui") String dui,
			@RequestParam("correo") String correo, @RequestParam("direccion") String direccion,
			@RequestParam("municipio") int municipio, @RequestParam("codigo") int codigo,
			@RequestParam("telefono") String telefono, @RequestParam("sexo") String sexo,
			@RequestParam("fNacimiento") String nacimiento) throws ParseException {

		try {

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = dateFormat.parse(nacimiento);

			Municipios m = new Municipios();
			m.setIdMuni(municipio);

			Direcciones d = new Direcciones();
			d.setIdDir(idDir);
			d.setDireccion(direccion);
			d.setMunicipios(m);
			direcciones.update(d);

			Personas p = new Personas();
			p.setIdPer(idPer);
			p.setNombre(nombre);
			p.setApellido(apellido);
			p.setDui(dui);
			p.setSexo(sexo);
			p.setCorreo(correo);
			p.setEstado('1');
			p.setDirecciones(d);
			p.setTelefono(telefono);

			p.setFNacimiento(date);
			p.setTipo(true);
			personas.update(p);

			Clientes c = new Clientes();
			c.setIdCliente(idCliente);
			c.setCodigoCl(codigo);
			c.setEstado(true);
			c.setPersonas(p);
			clientes.update(c);
			mav.addObject("msg", "<b style='color: Green;'>Exito Cliente  " + codigo + " Actualizado </br>");
		} catch (Exception e) {
			mav.addObject("msg", "<b style='color: red;'>Error inesperado</b>");
		}
		return readCliente();
	}

	// metodo para Desabilitar un cliente
	@RequestMapping(value = "/disableCliente", method = RequestMethod.GET)
	public ModelAndView disableCliente(@RequestParam("c") int cli, @RequestParam("co") int co, @RequestParam("p") int p,
			@RequestParam("estado") boolean estado) {
		Clientes c = new Clientes();
		Personas per = new Personas();
		per.setIdPer(p);

		c.setIdCliente(cli);
		c.setPersonas(per);
		c.setCodigoCl(co);
		c.setEstado(estado);
		clientes.update(c);
		String mensaje = "<div id=\"moo\" class=\"alert alert-success alert-dismissible\" role=\"alert\" auto-close=\"3000\">\n"
                + "  <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\"><span aria-hidden=\"true\">&times;</span>\n"
                + "  </button>\n"
                + "  Éxito! Registro actualizado...\n"
                + "</div>";
		mav.addObject("msg", mensaje);
		return readCliente();
	}

	// Lista de tipo de usuarios
	@RequestMapping(value = "/consultaUS")
	public ModelAndView vistaUsuario1() {
		usuarioimp = new UsuarioImp();
		List<Usuarios> lisu = new ArrayList<>();
		lisu = usuarioimp.findAll();
		mav.setViewName("updateusuario");
		mav.addObject("lisu", lisu);
		return mav;
	}
}
