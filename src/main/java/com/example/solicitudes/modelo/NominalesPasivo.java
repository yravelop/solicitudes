package com.example.solicitudes.modelo;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "RPR_NOMINALES_PASV")
public class NominalesPasivo extends EntidadBaseNoEditable implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 369018472225697975L;

	@EmbeddedId
	private NominalesPasivoPK pk;

	@Column(name = "PERS_IDENTIFICADOR")
	private Long persIdentificador;

	@Column(name = "COD_VALOR_MON")
	private Long codValorMon;

	@Column(name = "NOMINAL")
	private Long nominal;

	@Column(name = "FECHA_BASICO")
	private Date fechaBasico;

	@Column(name = "FECHA_PER_HASTA")
	private Date fechaPerHasta;

	@Column(name = "FECHA_VIG_HASTA")
	private LocalDateTime fechaVigHasta;

	public NominalesPasivo(String usuAct, LocalDateTime fchAct, NominalesPasivoPK pk, Long persIdentificador,
			Long codValorMon, Long nominal, Date fechaBasico, Date fechaPerHasta, LocalDateTime fechaVigHasta) {
		super();
		this.pk = pk;
		this.persIdentificador = persIdentificador;
		this.codValorMon = codValorMon;
		this.nominal = nominal;
		this.fechaBasico = fechaBasico;
		this.fechaPerHasta = fechaPerHasta;
		this.fechaVigHasta = fechaVigHasta;
	}

	public NominalesPasivo() {
		super();
	}

	public NominalesPasivoPK getPk() {
		return pk;
	}

	public void setPk(NominalesPasivoPK pk) {
		this.pk = pk;
	}

	public Long getPersIdentificador() {
		return persIdentificador;
	}

	public void setPersIdentificador(Long persIdentificador) {
		this.persIdentificador = persIdentificador;
	}

	public Long getCodValorMon() {
		return codValorMon;
	}

	public void setCodValorMon(Long codValorMon) {
		this.codValorMon = codValorMon;
	}

	public Long getNominal() {
		return nominal;
	}

	public void setNominal(Long nominal) {
		this.nominal = nominal;
	}

	public Date getFechaBasico() {
		return fechaBasico;
	}

	public void setFechaBasico(Date fechaBasico) {
		this.fechaBasico = fechaBasico;
	}

	public Date getFechaPerHasta() {
		return fechaPerHasta;
	}

	public void setFechaPerHasta(Date fechaPerHasta) {
		this.fechaPerHasta = fechaPerHasta;
	}

	public LocalDateTime getFechaVigHasta() {
		return fechaVigHasta;
	}

	public void setFechaVigHasta(LocalDateTime fechaVigHasta) {
		this.fechaVigHasta = fechaVigHasta;
	}

}
