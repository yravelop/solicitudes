package com.example.solicitudes.modelo;

import java.io.Serializable;
import java.sql.Date;
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
public class NominalesPasivoPK implements Serializable {

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

	@Column(name = "FECHA_PER_DESDE")
	private Date fechaPerDesde;

	@Column(name = "COD_RUBRO_LIQ")
	private Long codRubroLiq;

	@Column(name = "CATEG_NOMINAL")
	private Long categNominal;

	@Column(name = "FECHA_VIG_DESDE")
	private LocalDateTime fechaVigDesde;

	public NominalesPasivoPK(Beneficio beneficio, Date fechaPerDesde, Long codRubroLiq, Long categNominal,
			LocalDateTime fechaVigDesde) {
		super();
		this.beneficio = beneficio;
		this.fechaPerDesde = fechaPerDesde;
		this.codRubroLiq = codRubroLiq;
		this.categNominal = categNominal;
		this.fechaVigDesde = fechaVigDesde;
	}

	public NominalesPasivoPK() {
		super();
	}

	public Beneficio getBeneficio() {
		return beneficio;
	}

	public void setBeneficio(Beneficio beneficio) {
		this.beneficio = beneficio;
	}

	public Date getFechaPerDesde() {
		return fechaPerDesde;
	}

	public void setFechaPerDesde(Date fechaPerDesde) {
		this.fechaPerDesde = fechaPerDesde;
	}

	public Long getCodRubroLiq() {
		return codRubroLiq;
	}

	public void setCodRubroLiq(Long codRubroLiq) {
		this.codRubroLiq = codRubroLiq;
	}

	public Long getCategNominal() {
		return categNominal;
	}

	public void setCategNominal(Long categNominal) {
		this.categNominal = categNominal;
	}

	public LocalDateTime getFechaVigDesde() {
		return fechaVigDesde;
	}

	public void setFechaVigDesde(LocalDateTime fechaVigDesde) {
		this.fechaVigDesde = fechaVigDesde;
	}

}
