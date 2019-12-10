package com.telefonia.controls;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.telefonia.imp.ConfiguracionImp;
import com.telefonia.models.Configuracion;
import com.telefonia.utils.Dao;

@SuppressWarnings("unused")
@Controller
public class ManejoConf {

	ModelAndView mav = new ModelAndView();

	@Autowired
	private ConfiguracionImp conf;

	private Configuracion c1;
	private Configuracion c2;
	private Configuracion c3;

	private void init() {
		conf = new ConfiguracionImp();
		c3 = new Configuracion();
		c2 = new Configuracion();
		c1 = new Configuracion();
	}

	@RequestMapping(value = "/cargarCo")
	public ModelAndView conf() {
		init();

		c1 = conf.lisConfId(2);
		c2 = conf.lisConfId(3);
		c3 = conf.lisConfId(1);
		mav.addObject("c1", c1);
		mav.addObject("c2", c2);
		mav.addObject("c3", c3);
		mav.setViewName("config");
		return mav;
	}

	// mul Metodo Update Llamada
	@RequestMapping(value = "/mul", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView modit1(@RequestParam("t0l") int t0l, @RequestParam("t1l") BigDecimal t1l,
			@RequestParam("t2l") BigDecimal t2l, @RequestParam("t3l") BigDecimal t3l, ModelAndView mv) {
		Configuracion confi1 = new Configuracion();
		confi1.setIdConf(t0l);
		confi1.setTafifaNormal(t3l);
		confi1.setTarifaFijo(t2l);
		confi1.setTarifaMismaRed(t1l);
		conf.update(confi1);

		// mv.setViewName("config");
		// return mv;
		try {
			conf.update(confi1);
			mav.addObject("m", 1);
			mav.addObject("msm", "Datos Editados Correctamente");
			return conf();
		} catch (Exception e) {
			mv.addObject("m", 1);
			mv.addObject("msm", "Sucedio un Error");
			mv.setViewName("config");
			return mv;
		}
	}

	// mum Metodo Update Mensajes
	@RequestMapping(value = "/mum", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView modim1(@RequestParam("t0m") int t0m, @RequestParam("t1m") BigDecimal t1m,
			@RequestParam("t2m") BigDecimal t2m, @RequestParam("t3m") BigDecimal t3m, ModelAndView mv) {
		Configuracion confi2 = new Configuracion();
		confi2.setIdConf(t0m);
		confi2.setTafifaNormal(t3m);
		confi2.setTarifaFijo(t2m);
		confi2.setTarifaMismaRed(t1m);
		try {
			conf.update(confi2);
			mav.addObject("m", 1);
			mav.addObject("msm", "Datos Editados Correctamente");
			return conf();
		} catch (Exception e) {
			mv.addObject("m", 1);
			mv.addObject("msm", "Sucedio un Error");
			mv.setViewName("config");
			return mv;
		}

		// mv.setViewName("config");
		// return mv;

	}

	// mud Metodo Update Datos
	@RequestMapping(value = "/mud", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView modid1(@RequestParam("id") int id, @RequestParam("me") Double me, ModelAndView mv) {
		Configuracion confi3 = new Configuracion();
		confi3.setIdConf(id);
		confi3.setCosto(me);
		try {
			conf.update(confi3);
			// mv.setViewName("config");
			// return mv;
			mav.addObject("m", 1);
			mav.addObject("msm", "Datos Editados Correctamente");
			return conf();
		} catch (Exception e) {
			mv.addObject("m", 1);
			mv.addObject("msm", "Sucedio un Error");
			mv.setViewName("config");
			return mv;
		}

	}

}
