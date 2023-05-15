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
public class EstadoSolicitudPK implements Serializable {

	private static final long serialVersionUID = -1186768869931170648L;
	@LazyCollection(LazyCollectionOption.TRUE)
	@JoinColumn(name = "NRO_SOLICITUD", referencedColumnName = "NRO_SOLICITUD")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	private Solicitud solicitud;

	@LazyCollection(LazyCollectionOption.TRUE)
	@JoinColumn(name = "COD_EST_SOL_BEN", referencedColumnName = "COD_EST_SOL_BEN")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	private CodigoEstSol estSolBen;

	@Column(name = "FECHA_VIG_DESDE")
	private LocalDateTime fechaVigDesde;

	public EstadoSolicitudPK(Solicitud solicitud, CodigoEstSol estSolBen, LocalDateTime fechaVigDesde) {
		super();
		this.solicitud = solicitud;
		this.estSolBen = estSolBen;
		this.fechaVigDesde = fechaVigDesde;
	}

	public EstadoSolicitudPK() {
		super();
	}

	public Solicitud getNumSolicitud() {
		return solicitud;
	}

	public void setNumSolicitud(Solicitud numSolicitud) {
		this.solicitud = numSolicitud;
	}

	public CodigoEstSol getCodEstSolBen() {
		return estSolBen;
	}

	public void setCodEstSolBen(CodigoEstSol codEstSolBen) {
		this.estSolBen = codEstSolBen;
	}

	public LocalDateTime getFechaVigDesde() {
		return fechaVigDesde;
	}

	public void setFechaVigDesde(LocalDateTime fechaVigDesde) {
		this.fechaVigDesde = fechaVigDesde;
	}

}
