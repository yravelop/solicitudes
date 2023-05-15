package com.example.solicitudes.modelo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "RPR_BENEFICIOS")
public class Beneficio extends EntidadBaseNoEditable implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 369018472225697975L;

	@EmbeddedId
	private BeneficioPK pk;

	@Column(name = "FECHA_VIG_HASTA")
	private LocalDateTime fechaVigHasta;

	@Column(name = "FECHA_PER_HASTA")
	private LocalDateTime fechaPerHasta;

	@LazyCollection(LazyCollectionOption.TRUE)
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pk.beneficio")
	@Fetch(FetchMode.SELECT)
	private List<BeneficioCausales> beneficioCausal;

	@LazyCollection(LazyCollectionOption.TRUE)
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pk.beneficio")
	@Fetch(FetchMode.SELECT)
	private List<NominalesPasivo> nominalesPasivos;

	public Beneficio(BeneficioPK pk, LocalDateTime fechaVigHasta, LocalDateTime fechaPerHasta) {
		super();
		this.pk = pk;
		this.fechaVigHasta = fechaVigHasta;
		this.fechaPerHasta = fechaPerHasta;

	}

	public List<NominalesPasivo> getNominalesPasivos() {
		return nominalesPasivos;
	}

	public void setNominalesPasivos(List<NominalesPasivo> nominalesPasivos) {
		this.nominalesPasivos = nominalesPasivos;
	}

	public Beneficio() {
		super();
	}

	public BeneficioPK getPk() {
		return pk;
	}

	public void setPk(BeneficioPK pk) {
		this.pk = pk;
	}

	public LocalDateTime getFechaVigHasta() {
		return fechaVigHasta;
	}

	public void setFechaVigHasta(LocalDateTime fechaVigHasta) {
		this.fechaVigHasta = fechaVigHasta;
	}

	public LocalDateTime getFechaPerHasta() {
		return fechaPerHasta;
	}

	public void setFechaPerHasta(LocalDateTime fechaPerHasta) {
		this.fechaPerHasta = fechaPerHasta;
	}

	public List<BeneficioCausales> getBeneficioCausal() {
		return beneficioCausal;
	}

	public void setBeneficioCausal(List<BeneficioCausales> beneficioCausal) {
		this.beneficioCausal = beneficioCausal;
	}

}
