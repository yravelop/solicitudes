package com.example.solicitudes.modelo;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "RPR_ESTADO_SOLIC")
public class EstadoSolicitud extends EntidadBaseNoEditable implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 369018472225697975L;

	@EmbeddedId
	private EstadoSolicitudPK pk;

	@Column(name = "OBSERVACIONES")
	private String observasion;

	@Column(name = "COD_VIA_INGRESO")
	private Long codViaIngreso;

	@Column(name = "COD_AGENCIA")
	private Long codAgencia;

	@Column(name = "FECHA_VIG_HASTA")
	private LocalDateTime fechaVigHasta;

	@Column(name = "COD_CAUSAL_BENEF")
	private Long codCausalBenef;

	@Column(name = "COD_LUGAR")
	private Long codLugar;

	public EstadoSolicitud(EstadoSolicitudPK pk, String observasion, Long codViaIngreso, Long codAgencia,
			LocalDateTime fechaVigHasta, Long codCausalBenef, Long codLugar) {
		this.pk = pk;
		this.observasion = observasion;
		this.codViaIngreso = codViaIngreso;
		this.codAgencia = codAgencia;
		this.fechaVigHasta = fechaVigHasta;
		this.codCausalBenef = codCausalBenef;
		this.codLugar = codLugar;
	}

	public EstadoSolicitud() {
		super();
	}

	public String getObservasion() {
		return observasion;
	}

	public void setObservasion(String observasion) {
		this.observasion = observasion;
	}

	public Long getCodViaIngreso() {
		return codViaIngreso;
	}

	public void setCodViaIngreso(Long codViaIngreso) {
		this.codViaIngreso = codViaIngreso;
	}

	public Long getCodAgencia() {
		return codAgencia;
	}

	public void setCodAgencia(Long codAgencia) {
		this.codAgencia = codAgencia;
	}

	public LocalDateTime getFechaVigHasta() {
		return fechaVigHasta;
	}

	public void setFechaVigHasta(LocalDateTime fechaVigHasta) {
		this.fechaVigHasta = fechaVigHasta;
	}

	public Long getCodCausalBenef() {
		return codCausalBenef;
	}

	public void setCodCausalBenef(Long codCausalBenef) {
		this.codCausalBenef = codCausalBenef;
	}

	public Long getCodLugar() {
		return codLugar;
	}

	public void setCodLugar(Long codLugar) {
		this.codLugar = codLugar;
	}

	public EstadoSolicitudPK getPk() {
		return pk;
	}

	public void setPk(EstadoSolicitudPK pk) {
		this.pk = pk;
	}

}
