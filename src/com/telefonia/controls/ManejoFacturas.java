package com.telefonia.controls;

import java.util.Date;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.telefonia.imp.ContratosImp;
import com.telefonia.imp.DescripcionImp;
import com.telefonia.imp.DetalleImp;
import com.telefonia.imp.FacturasImp;
import com.telefonia.models.Contratos;
import com.telefonia.models.Descripcion;
import com.telefonia.models.Detalle;
import com.telefonia.models.Facturas;
import com.telefonia.models.Usuarios;

@Controller
public class ManejoFacturas {

	ModelAndView mav = new ModelAndView();

	private Date emision = new Date();
//	private String inicio;
//	private String fin;

	@Autowired
	private ContratosImp contratos;

	@Autowired
	private DescripcionImp descripImp;

	@Autowired
	private DetalleImp det;

	@Autowired
	private FacturasImp facturas;

	@RequestMapping(value = "generarfactura", method = RequestMethod.GET)
	public ModelAndView facturas() {
		mav.setViewName("generarfactura");
		return mav;
	}
	
	@RequestMapping(value = "/generarF", method = RequestMethod.POST)
	public ModelAndView generarFactura(@RequestParam("i") String inicio, @RequestParam("f") String fin,
			@RequestParam("us") int us) {
		try {
			Detalle dt;
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(emision);
			calendar.add(Calendar.DAY_OF_YEAR, 10);
			contratos = new ContratosImp();
			descripImp = new DescripcionImp();
			facturas = new FacturasImp();
			det = new DetalleImp();
			List<Contratos> listCon = contratos.contratosXEstado(1);
			for (int i = 0; i < listCon.size(); i++) {
				Facturas fac = new Facturas();
				Contratos c = new Contratos();
				Usuarios u = new Usuarios();
				u.setIdUsu(us);
				c = listCon.get(i);

				fac.setFechEmision(emision);
				fac.setFechLimite(calendar.getTime());
				fac.setEstado(true);
				fac.setUsuarios(u);
				fac.setContratos(c);
				/* Guarda las facturas */
				facturas.save(fac);
			}
			
			List<Facturas> listFac = facturas.facturasFecha();
			int numero = listFac.size();
			//	List<Descripcion> detalles = desim.descripciones();
			for (int i = 0; i < listFac.size(); i++) {
				int d=listFac.get(i).getContratos().getIdContrat();
				System.out.println("Llega 0.3 "+d);
				
				List<Descripcion> detxf = descripImp.listadoDes(d);
				System.out.println("Paso");
				for (int j = 0; j < detxf.size(); j++) {
					dt = new Detalle();
					dt.setFacturas(listFac.get(i));
					dt.setDescripcion(detxf.get(j));
					det.save(dt);
				}
			}

			mav.setViewName("generarfactura");
			String  mensaje = "<div class=\"alert alert-success alert-dismissible fade show\" role=\"alert\"><strong>Exito!</strong> Se generaron "+ numero +" facturas correspondientes a este mes.<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\"><span aria-hidden=\"true\">&times;</span></button></div>";
			mav.addObject("mensaje", mensaje);
			return mav;
//			List<Clientes> client = facim.obtenerClientes();
//			List<Object[]> det = facim.obtenerDetalles(client);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		
	}

}
