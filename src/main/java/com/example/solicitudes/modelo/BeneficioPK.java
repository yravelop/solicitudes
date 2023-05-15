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
public class BeneficioPK implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1186768869931170648L;

	@LazyCollection(LazyCollectionOption.TRUE)
	@JoinColumn(name = "NRO_SOLICITUD", referencedColumnName = "NRO_SOLICITUD")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	private Solicitud solicitud;

	@Column(name = "FECHA_VIG_DESDE")
	private LocalDateTime fechaVigDesde;

	@Column(name = "FECHA_PER_DESDE")
	private LocalDateTime fechaPerDesde;

	public BeneficioPK(Solicitud solicitud, LocalDateTime fechaVigDesde, LocalDateTime fechaPerDesde) {
		super();
		this.solicitud = solicitud;
		this.fechaVigDesde = fechaVigDesde;
		this.fechaPerDesde = fechaPerDesde;
	}

	public BeneficioPK() {
		super();
	}

	public Solicitud getSolicitud() {
		return solicitud;
	}

	public void setSolicitud(Solicitud solicitud) {
		this.solicitud = solicitud;
	}

	public LocalDateTime getFechaVigDesde() {
		return fechaVigDesde;
	}

	public void setFechaVigDesde(LocalDateTime fechaVigDesde) {
		this.fechaVigDesde = fechaVigDesde;
	}

	public LocalDateTime getFechaPerDesde() {
		return fechaPerDesde;
	}

	public void setFechaPerDesde(LocalDateTime fechaPerDesde) {
		this.fechaPerDesde = fechaPerDesde;
	}

}
