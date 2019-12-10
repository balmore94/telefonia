package com.telefonia.controls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.telefonia.imp.ConsumoExImp;
import com.telefonia.imp.DetalleImp;
import com.telefonia.imp.FacturasImp;
import com.telefonia.models.Descripcion;
import com.telefonia.models.Detalle;
import com.telefonia.models.Facturas;

@SuppressWarnings("unused")
@Controller
public class Pagos {

	ModelAndView mav = new ModelAndView();

	private String dato;
	// private String numero;
	private Facturas facturas = new Facturas();
	private String direccion = "pagos";

	@Autowired
	private FacturasImp fac;

	@Autowired
	private DetalleImp det;
	
	@Autowired
	private ConsumoExImp cextra;

	private void init() {
		dato = new String();
		fac = new FacturasImp();
		det = new DetalleImp();
	}

	@RequestMapping(value = "/pagos")
	public ModelAndView pag() {
		init();
		dato = "<div class=\"alert alert-primary\" role=\"alert\">\r\n" + "  REALIZE UNA BUSQUEDA...!\r\n" + "</div>";
		mav.addObject("da", dato);
		mav.setViewName(direccion);
		return mav;
	}

	@RequestMapping(value = "/mosF", method = RequestMethod.GET)
	public ModelAndView mostrar(@RequestParam("factura") int fact) {
		init();
		//--
		cextra=new ConsumoExImp();
		//--
		double cargo[] = fac.calcular(fact);
		double costo = 0;
		
		List<Detalle> listaDet = det.listadoPorFac(fact);
		List<Object[]> listaDes = fac.obtenerDetalle(listaDet);
		//Utilizo el codigo de cobros extras
		//Y obtenfo los datos ya sumados.
		double ss=cextra.calculosCD(listaDet);
		//
		Object v[];
		// Obtencion de pagos por Servicio
		for (int i = 0; i < listaDes.size(); i++) {
			v = listaDes.get(i);
			double c = Double.parseDouble(v[3].toString());
			costo = costo + c;
		}
		mav.addObject("f",fact);
		mav.addObject("da", costo);
		mav.addObject("car", cargo[0]);
		mav.addObject("dia", cargo[1]);
		mav.addObject("total",costo+cargo[0]+ss);
		mav.addObject("e",ss);
//		System.out.println("Costo total ---- " + costo+ss);
		mav.setViewName("detalle");
		return mav;
	}
	
	@RequestMapping(value="/pagar",method = RequestMethod.POST)
	public ModelAndView pagar(@RequestParam("fa") int fa,ModelAndView mv) {
		fac.pagar(fa);
		return mv;
	}

	@RequestMapping(value = "/carf", method = RequestMethod.POST)
	public ModelAndView cargar(@RequestParam("nf") int nu, ModelAndView mv) {
		System.out.println("LLEGADA DE NUMERO " + nu);
		init();
		facturas = fac.busquedaN(nu);
		if (facturas.getIdFac() > 0) {
			dato = "<table class=\"table table-bordered table-responsive-md\"><thead class=\"thead text-white\" style=\"background: #81ab35\"><tr><th scope=\"col\">#Factura</th><th scope=\"col\">Fecha de Emision</th><th scope=\"col\">Fecha Limite</th><th scope=\"col\">Opcion</th></tr></thead><tbody>\r\n"
					+ "						<tr>\r\n" + "							<th scope=\"row\">"
					+ facturas.getIdFac() + "</th>\r\n" + "							<td>" + facturas.getFechEmision()
					+ "</td>\r\n" + "							<td>" + facturas.getFechLimite() + "</td>\r\n"
					+ "							<td><a class='btn btn-color' href='mosF?factura=" + facturas.getIdFac()
					+ "'>Realizar Pago</a>\r\n" + "						</tr>\r\n" + "					</tbody>\r\n"
					+ "				</table>";
			mv.addObject("da", dato);
		} else {
			dato = "<div class=\"alert alert-danger\" role=\"alert\">\r\n" + "Factura No Encontrada!\r\n" + "</div>";
			mv.addObject("da", dato);
		}

		mv.setViewName(direccion);
		return mv;
	}

}