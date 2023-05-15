package com.example.solicitudes.modelo;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Embeddable
public class RolesPersSolicitudPK implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1186768869931170648L;

	@LazyCollection(LazyCollectionOption.TRUE)
	@JoinColumn(name = "NRO_SOLICITUD", referencedColumnName = "NRO_SOLICITUD")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	private Solicitud solicitud;

	@Column(name = "PERS_IDENTIFICADOR")
	private Long idenficador;

	@Column(name = "COD_ROL_PERSONA")
	private Long codRolPersona;

	@Column(name = "FECHA_VIG_DESDE")
	private LocalDateTime fechaVigDesde;

	public RolesPersSolicitudPK(Solicitud solicitud, Long idenficador, Long codRolPersona,
			LocalDateTime fechaVigDesde) {
		super();
		this.solicitud = solicitud;
		this.idenficador = idenficador;
		this.codRolPersona = codRolPersona;
		this.fechaVigDesde = fechaVigDesde;
	}

	public RolesPersSolicitudPK() {
		super();
	}

	public Solicitud getSolicitud() {
		return solicitud;
	}

	public void setSolicitud(Solicitud solicitud) {
		this.solicitud = solicitud;
	}

	public Long getIdenficador() {
		return idenficador;
	}

	public void setIdenficador(Long idenficador) {
		this.idenficador = idenficador;
	}

	public Long getCodRolPersona() {
		return codRolPersona;
	}

	public void setCodRolPersona(Long codRolPersona) {
		this.codRolPersona = codRolPersona;
	}

	public LocalDateTime getFechaVigDesde() {
		return fechaVigDesde;
	}

	public void setFechaVigDesde(LocalDateTime fechaVigDesde) {
		this.fechaVigDesde = fechaVigDesde;
	}

}
