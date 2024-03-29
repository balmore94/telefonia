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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * TipoContrato generated by hbm2java
 */
@Entity
@SuppressWarnings("serial")
@Table(name = "tipo_contrato", catalog = "si_telefonia")
public class TipoContrato implements java.io.Serializable {

	private Integer idTpcontrat;
	private String nombreTipoc;
	private String descripcion;
	private List<Contratos> contratoses = new ArrayList<Contratos>();

	public TipoContrato() {
	}

	public TipoContrato(String nombreTipoc, String descripcion) {
		this.nombreTipoc = nombreTipoc;
		this.descripcion = descripcion;
	}

	public TipoContrato(String nombreTipoc, String descripcion, List<Contratos> contratoses) {
		this.nombreTipoc = nombreTipoc;
		this.descripcion = descripcion;
		this.contratoses = contratoses;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id_tpcontrat", unique = true, nullable = false)
	public Integer getIdTpcontrat() {
		return this.idTpcontrat;
	}

	public void setIdTpcontrat(Integer idTpcontrat) {
		this.idTpcontrat = idTpcontrat;
	}

	@Column(name = "nombre_tipoc", nullable = false, length = 200)
	public String getNombreTipoc() {
		return this.nombreTipoc;
	}

	public void setNombreTipoc(String nombreTipoc) {
		this.nombreTipoc = nombreTipoc;
	}

	@Column(name = "descripcion", nullable = false, length = 65535)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tipoContrato")
	public List<Contratos> getContratoses() {
		return this.contratoses;
	}

	public void setContratoses(List<Contratos> contratoses) {
		this.contratoses = contratoses;
	}

}
