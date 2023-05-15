package com.example.solicitudes.modelo;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "COD_CAUSAL_BENEF")
public class CodCausalBeneficio extends EntidadBaseNoEditable implements Serializable {

	private static final long serialVersionUID = 4297123372120915920L;

	@Id
	@Column(name = "COD_CAUSAL_BENEF")
	private Long codCausalBeneficio;

	@Basic(optional = false)
	@Column(name = "DESCRIPCION")
	private String descripcion;

	public CodCausalBeneficio(String usuAct, LocalDateTime fchAct, Long codCausalBeneficio, String descripcion) {
		super(usuAct, fchAct);
		this.codCausalBeneficio = codCausalBeneficio;
		this.descripcion = descripcion;
	}

	public CodCausalBeneficio() {
		super();
	}

	public Long getCodCausalBeneficio() {
		return codCausalBeneficio;
	}

	public void setCodCausalBeneficio(Long codCausalBeneficio) {
		this.codCausalBeneficio = codCausalBeneficio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
