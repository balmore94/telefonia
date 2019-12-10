package com.telefonia.utils;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import com.telefonia.models.Clientes;
import com.telefonia.models.Configuracion;
import com.telefonia.models.ConsExtra;
import com.telefonia.models.Contratos;
import com.telefonia.models.CtrlServ;
import com.telefonia.models.Departamentos;
import com.telefonia.models.Descripcion;
import com.telefonia.models.Detalle;
import com.telefonia.models.Direcciones;
import com.telefonia.models.Facturas;
import com.telefonia.models.Municipios;
import com.telefonia.models.Personas;
import com.telefonia.models.Servicios;
import com.telefonia.models.TipoContrato;
import com.telefonia.models.TipoUsu;
import com.telefonia.models.Usuarios;

public class HibernateUtil {
	private static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try {
				Configuration configuration = new Configuration();
				// Hibernate settings equivalent to hibernate.cfg.xml's properties
				Properties settings = new Properties();
				settings.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
				settings.put(Environment.URL, "jdbc:mysql://localhost:3306/si_telefonia?useSSL=false");
				settings.put(Environment.USER, "root");
				settings.put(Environment.PASS, "root");
				settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
				settings.put(Environment.SHOW_SQL, "true");
				settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
				// settings.put(Environment.HBM2DDL_AUTO, "create-drop");
				configuration.setProperties(settings);
				configuration.addAnnotatedClass(Clientes.class);
				configuration.addAnnotatedClass(Configuracion.class);
				configuration.addAnnotatedClass(ConsExtra.class);
				configuration.addAnnotatedClass(Contratos.class);
				configuration.addAnnotatedClass(CtrlServ.class);
				configuration.addAnnotatedClass(Departamentos.class);
				configuration.addAnnotatedClass(Detalle.class);
				configuration.addAnnotatedClass(Descripcion.class);
				configuration.addAnnotatedClass(Direcciones.class);
				configuration.addAnnotatedClass(Facturas.class);
				configuration.addAnnotatedClass(Municipios.class);
				configuration.addAnnotatedClass(Personas.class);
				configuration.addAnnotatedClass(Servicios.class);
				configuration.addAnnotatedClass(TipoContrato.class);
				configuration.addAnnotatedClass(TipoUsu.class);
				configuration.addAnnotatedClass(Usuarios.class);
				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
						.applySettings(configuration.getProperties()).build();
				sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sessionFactory;
	}
}