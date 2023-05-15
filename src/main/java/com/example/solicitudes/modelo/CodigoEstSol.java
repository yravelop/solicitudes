package com.example.solicitudes.modelo;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="COD_EST_SOL")
public class CodigoEstSol extends EntidadBaseNoEditable implements Serializable{
	
	private static final long serialVersionUID = 4297123372120915920L;
	
	@Id
	@Column(name = "COD_EST_SOL_BEN")
	private Long id;
	
	@Basic(optional = false)
	@Column(name = "DESCRIPCION")
	private String descripcion;
	
	@Column(name = "PERMITE_CAMBIO")
	private String permiteCambio;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getPermiteCambio() {
		return permiteCambio;
	}

	public void setPermiteCambio(String permiteCambio) {
		this.permiteCambio = permiteCambio;
	}

	public CodigoEstSol(String usuAct, LocalDateTime fchAct, Long id, String descripcion, String permiteCambio) {
		super(usuAct, fchAct);
		this.id = id;
		this.descripcion = descripcion;
		this.permiteCambio = permiteCambio;
	}

	public CodigoEstSol() {
		super();
	}
	
	

}
