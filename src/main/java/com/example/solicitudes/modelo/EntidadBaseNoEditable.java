package com.example.solicitudes.modelo;

import java.time.LocalDateTime;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;

@MappedSuperclass
public class EntidadBaseNoEditable {

	@Basic(optional = false)
	@Column(name = "UACT")
	private String usuAct;

	@Basic(optional = false)
	@Column(name = "FACT")
	private LocalDateTime fchAct;

	@PrePersist
	protected void prePersist() {
		this.setFchAct(LocalDateTime.now());
		this.setUsuAct("user");
	}

	public String getUsuAct() {
		return usuAct;
	}

	public void setUsuAct(String usuAct) {
		this.usuAct = usuAct;
	}

	public LocalDateTime getFchAct() {
		return fchAct;
	}

	public void setFchAct(LocalDateTime fchAct) {
		this.fchAct = fchAct;
	}

	public EntidadBaseNoEditable(String usuAct, LocalDateTime fchAct) {
		super();
		this.usuAct = usuAct;
		this.fchAct = fchAct;
	}

	public EntidadBaseNoEditable() {
		super();
	}

}