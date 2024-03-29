package com.telefonia.models;
// Generated 11-22-2019 08:26:25 AM by Hibernate Tools 5.2.12.Final

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * ConsExtra generated by hbm2java
 */
@Entity
@SuppressWarnings("serial")
@Table(name = "cons_extra", catalog = "si_telefonia")
public class ConsExtra implements java.io.Serializable {

	private Integer idConsumo;
	private Descripcion descripcion;
	private BigDecimal consumo;

	public ConsExtra() {
	}

	public ConsExtra(Descripcion descripcion, BigDecimal consumo) {
		this.descripcion = descripcion;
		this.consumo = consumo;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id_consumo", unique = true, nullable = false)
	public Integer getIdConsumo() {
		return this.idConsumo;
	}

	public void setIdConsumo(Integer idConsumo) {
		this.idConsumo = idConsumo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "descripcion", nullable = false)
	public Descripcion getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(Descripcion descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = "consumo", nullable = false, precision = 4)
	public BigDecimal getConsumo() {
		return this.consumo;
	}

	public void setConsumo(BigDecimal consumo) {
		this.consumo = consumo;
	}

}
