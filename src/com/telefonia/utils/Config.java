package com.telefonia.utils;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.telefonia.imp.ClienteImp;
import com.telefonia.imp.ConfiguracionImp;
import com.telefonia.imp.ConsumoExImp;
import com.telefonia.imp.ContratosImp;
import com.telefonia.imp.ControlSerImp;
import com.telefonia.imp.DepartamentosImp;
import com.telefonia.imp.DescripcionImp;
import com.telefonia.imp.DetalleImp;
import com.telefonia.imp.DireccionesImp;
import com.telefonia.imp.FacturasImp;
import com.telefonia.imp.MunicipiosImp;
import com.telefonia.imp.PersonaImp;
import com.telefonia.imp.TipoUsuarioImp;
import com.telefonia.imp.UsuarioImp;
import com.telefonia.models.Clientes;
import com.telefonia.models.Contratos;
import com.telefonia.models.Departamentos;
import com.telefonia.models.Direcciones;
import com.telefonia.models.Facturas;
import com.telefonia.models.Municipios;
import com.telefonia.models.Personas;
import com.telefonia.models.TipoUsu;
import com.telefonia.models.Usuarios;

@Configuration
@EnableWebMvc
@ComponentScan("com.telefonia")
@EnableTransactionManagement(proxyTargetClass = true)
public class Config {

	@Bean
	InternalResourceViewResolver viewRes() {
		InternalResourceViewResolver r = new InternalResourceViewResolver();
		r.setPrefix("/WEB-INF/views/");
		r.setSuffix(".jsp");
		return r;
	}

	@Bean
	public SessionFactory getConex() {
		return HibernateUtil.getSessionFactory();
	}

	@Bean
	public Dao<Personas> personas() {
		return new PersonaImp();
	}

	@Bean
	public ConfiguracionImp conf() {
		return new ConfiguracionImp();
	}

	@Bean
	public Dao<Departamentos> departamentos() {
		return new DepartamentosImp();
	}

	@Bean
	public Dao<Municipios> municipios() {
		return new MunicipiosImp();
	}

	@Bean
	public Dao<Clientes> clientes() {
		return new ClienteImp();
	}

	@Bean
	public ClienteImp client() {
		return new ClienteImp();
	}

	@Bean
	public Dao<Direcciones> direcciones() {
		return new DireccionesImp();
	}

	@Bean
	public Dao<Facturas> facturas() {
		return new FacturasImp();
	}

	@Bean
	public Dao<Usuarios> usuarios() {
		return new UsuarioImp();
	}

	@Bean
	public Dao<TipoUsu> tipou() {
		return new TipoUsuarioImp();
	}

	@Bean
	public ContratosImp contratos() {
		return new ContratosImp();
	}

	@Bean
	public Dao<Contratos> contrato() {
		return new ContratosImp();
	}

	@Bean
	public DetalleImp det() {
		return new DetalleImp();
	}

	/*
	 * @Bean public DescripcionImp descripIMP() { return new DescripcionImp(); }
	 */
	@Bean
	public DescripcionImp descripImp() {
		return new DescripcionImp();
	}

	@Bean
	public UsuarioImp usuarioimp() {
		return new UsuarioImp();
	}

	@Bean
	public ControlSerImp crs() {
		return new ControlSerImp();
	}

	@Bean
	public ConsumoExImp cextra() {
		return new ConsumoExImp();
	}

}
