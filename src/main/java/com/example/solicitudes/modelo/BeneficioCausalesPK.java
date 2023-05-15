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
public class BeneficioCausalesPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 369018472225697975L;

	@LazyCollection(LazyCollectionOption.TRUE)
	@JoinColumn(name = "NRO_SOLICITUD", referencedColumnName = "NRO_SOLICITUD")
	@JoinColumn(name = "BENEF_FECHA_PER_DESDE", referencedColumnName = "FECHA_PER_DESDE")
	@JoinColumn(name = "BENEF_FECHA_VIG_DESDE", referencedColumnName = "FECHA_VIG_DESDE")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	private Beneficio beneficio;

	@Column(name = "FECHA_VIG_DESDE")
	private LocalDateTime fechaVigDesde;

	@Column(name = "FECHA_PER_DESDE")
	private LocalDateTime fechaPerDesde;

	@LazyCollection(LazyCollectionOption.TRUE)
	@JoinColumn(name = "COD_CAUSAL_BENEF", referencedColumnName = "COD_CAUSAL_BENEF")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	private CodCausalBeneficio codCausalBenef;

	public BeneficioCausalesPK(Beneficio beneficio, LocalDateTime fechaVigDesde, LocalDateTime fechaPerDesde) {
		super();
		this.beneficio = beneficio;
		this.fechaVigDesde = fechaVigDesde;
		this.fechaPerDesde = fechaPerDesde;
	}

	public BeneficioCausalesPK() {
		super();
	}

	public Beneficio getBeneficio() {
		return beneficio;
	}

	public void setBeneficio(Beneficio beneficio) {
		this.beneficio = beneficio;
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

	public CodCausalBeneficio getCodCausalBenef() {
		return codCausalBenef;
	}

	public void setCodCausalBenef(CodCausalBeneficio codCausalBenef) {
		this.codCausalBenef = codCausalBenef;
	}

}
