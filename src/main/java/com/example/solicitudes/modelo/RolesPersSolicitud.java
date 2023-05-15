package com.example.solicitudes.modelo;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "RPR_ROLES_PERS_SOLIC")
public class RolesPersSolicitud extends EntidadBaseNoEditable implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 369018472225697975L;

	@EmbeddedId
	private RolesPersSolicitudPK pk;

	@Column(name = "FECHA_VIG_HASTA")
	private LocalDateTime fechaVigHasta;

	public RolesPersSolicitud() {
		super();
	}

	public RolesPersSolicitud(String usuAct, LocalDateTime fchAct, RolesPersSolicitudPK pk,
			LocalDateTime fechaVigHasta) {
		super(usuAct, fchAct);
		this.pk = pk;
		this.fechaVigHasta = fechaVigHasta;
	}

	public RolesPersSolicitudPK getPk() {
		return pk;
	}

	public void setPk(RolesPersSolicitudPK pk) {
		this.pk = pk;
	}

	public LocalDateTime getFechaVigHasta() {
		return fechaVigHasta;
	}

	public void setFechaVigHasta(LocalDateTime fechaVigHasta) {
		this.fechaVigHasta = fechaVigHasta;
	}

}
