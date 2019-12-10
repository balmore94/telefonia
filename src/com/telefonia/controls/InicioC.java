package com.telefonia.controls;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.telefonia.imp.UsuarioImp;
import com.telefonia.models.Usuarios;

@Controller
public class InicioC {

	ModelAndView model = new ModelAndView();

	@Autowired
	private UsuarioImp usuarioimp;

	private Usuarios logeado = new Usuarios();

	@RequestMapping(value = "/inicio", method = RequestMethod.GET)
	public ModelAndView newPersona() {
		model.setViewName("inicio");
		return model;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login() {
		model.setViewName("login");
		return model;
	}

	// Metodo de inicio de sesion
	@PostMapping(value = "/login")
	public ModelAndView loginIn(@RequestParam("user") String user, @RequestParam("pass") String pass,
			HttpServletRequest request) {

		Usuarios us = new Usuarios();
		us.setUsuario(user);

		logeado = usuarioimp.login(us, pass);
		pass = new String();
		user = new String();

		if (logeado == null) {
			model.addObject("msg", "<b style='color: red;'>Credenciales invalidas</b>");
			model.setViewName("login");
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("userLoggin", logeado);
			model.setViewName("redirect:/inicio");
		}
		return model;
	}

	// Metodo para cerrar session
	@RequestMapping(value = "/closelogin", method = RequestMethod.GET)
	public ModelAndView loginIn(HttpSession session) {
		
		session.removeAttribute("userLoggin");
		session.setAttribute("userLoggin", null);
		session.invalidate();
		logeado = new Usuarios();
		model.addObject("userLoggin",null);
		model.setViewName("redirect:/inicio");
		return model;
	}

}
