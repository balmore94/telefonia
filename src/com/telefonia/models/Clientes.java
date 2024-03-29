package com.telefonia.models;
// Generated 11-22-2019 08:26:25 AM by Hibernate Tools 5.2.12.Final

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Clientes generated by hbm2java
 */
@Entity
@SuppressWarnings("serial")
@Table(name = "clientes", catalog = "si_telefonia")
public class Clientes implements java.io.Serializable {

	private Integer idCliente;
	private Personas personas;
	private int codigoCl;
	private boolean estado;
	private List<Contratos> contratoses = new ArrayList<Contratos>();

	public Clientes() {
	}

	public Clientes(Personas personas, int codigoCl, boolean estado) {
		this.personas = personas;
		this.codigoCl = codigoCl;
		this.estado = estado;
	}

	public Clientes(Personas personas, int codigoCl, boolean estado, List<Contratos> contratoses) {
		this.personas = personas;
		this.codigoCl = codigoCl;
		this.estado = estado;
		this.contratoses = contratoses;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id_cliente", unique = true, nullable = false)
	public Integer getIdCliente() {
		return this.idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "persona", nullable = false)
	public Personas getPersonas() {
		return this.personas;
	}

	public void setPersonas(Personas personas) {
		this.personas = personas;
	}

	@Column(name = "codigo_cl", nullable = false)
	public int getCodigoCl() {
		return this.codigoCl;
	}

	public void setCodigoCl(int codigoCl) {
		this.codigoCl = codigoCl;
	}

	@Column(name = "estado", nullable = false)
	public boolean isEstado() {
		return this.estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "clientes")
	public List<Contratos> getContratoses() {
		return this.contratoses;
	}

	public void setContratoses(List<Contratos> contratoses) {
		this.contratoses = contratoses;
	}

}
