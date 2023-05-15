package com.example.solicitudes.modelo;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "RPR_BENEF_CAUSALES")
public class BeneficioCausales extends EntidadBaseNoEditable implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 369018472225697975L;

	@EmbeddedId
	private BeneficioCausalesPK pk;

	@Column(name = "FECHA_VIG_HASTA")
	private LocalDateTime fechaVigHasta;

	@Column(name = "FECHA_PER_HASTA")
	private LocalDateTime fechaPerHasta;

	public BeneficioCausales(String usuAct, LocalDateTime fchAct, BeneficioCausalesPK pk, LocalDateTime fechaVigHasta,
			LocalDateTime fechaPerHasta) {
		super(usuAct, fchAct);
		this.pk = pk;
		this.fechaVigHasta = fechaVigHasta;
		this.fechaPerHasta = fechaPerHasta;
	}

	public BeneficioCausales() {
		super();
	}

	public BeneficioCausalesPK getPk() {
		return pk;
	}

	public void setPk(BeneficioCausalesPK pk) {
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

}
