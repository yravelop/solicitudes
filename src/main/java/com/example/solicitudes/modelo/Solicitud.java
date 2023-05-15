package com.example.solicitudes.modelo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "RPR_SOLICITUDES")
public class Solicitud extends EntidadBaseNoEditable implements Serializable {

	private static final long serialVersionUID = 4297123372120915920L;
	@Id
	@Column(name = "NRO_SOLICITUD")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long nrosolicitud;

	@Column(name = "FECHA_SOLICITUD")
	private LocalDateTime fechaSolicitud;

	@Column(name = "COD_TIPO_SOLICITUD")
	private Long codTipoSolicitud;

	@Column(name = "COD_BENEFICIO")
	private Long codBeneficio;

	@Column(name = "PROCEDENCIA")
	private String procedencia;

	@LazyCollection(LazyCollectionOption.TRUE)
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pk.solicitud")
	@Fetch(FetchMode.SELECT)
	private List<RolesPersSolicitud> roles;

	@LazyCollection(LazyCollectionOption.TRUE)
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pk.solicitud")
	@Fetch(FetchMode.SELECT)
	private List<EstadoSolicitud> estado;
	
	@LazyCollection(LazyCollectionOption.TRUE)
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pk.solicitud")
	@Fetch(FetchMode.SELECT)
	private List<Beneficio> beneficio;

	public Solicitud(String usuAct, LocalDateTime fchAct, Long nrosolicitud, LocalDateTime fechaSolicitud,
			Long codTipoSolicitud, Long codBeneficio, String procedencia) {
		super(usuAct, fchAct);
		this.nrosolicitud = nrosolicitud;
		this.fechaSolicitud = fechaSolicitud;
		this.codTipoSolicitud = codTipoSolicitud;
		this.codBeneficio = codBeneficio;
		this.procedencia = procedencia;

	}

	public Solicitud() {
		super();
	}

	public List<EstadoSolicitud> getEstado() {
		return estado;
	}

	public void setEstado(List<EstadoSolicitud> estado) {
		this.estado = estado;
	}

	public List<RolesPersSolicitud> getRoles() {
		return roles;
	}

	public void setRoles(List<RolesPersSolicitud> roles) {
		this.roles = roles;
	}

	public Long getNrosolicitud() {
		return nrosolicitud;
	}

	public void setNrosolicitud(Long nrosolicitud) {
		this.nrosolicitud = nrosolicitud;
	}

	public LocalDateTime getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(LocalDateTime fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public Long getCodTipoSolicitud() {
		return codTipoSolicitud;
	}

	public void setCodTipoSolicitud(Long codTipoSolicitud) {
		this.codTipoSolicitud = codTipoSolicitud;
	}

	public Long getCodBeneficio() {
		return codBeneficio;
	}

	public void setCodBeneficio(Long codBeneficio) {
		this.codBeneficio = codBeneficio;
	}

	public String getProcedencia() {
		return procedencia;
	}

	public void setProcedencia(String procedencia) {
		this.procedencia = procedencia;
	}
	

	public List<Beneficio> getBeneficio() {
		return beneficio;
	}

	public void setBeneficio(List<Beneficio> beneficio) {
		this.beneficio = beneficio;
		
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nrosolicitud == null) ? 0 : nrosolicitud.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Solicitud other = (Solicitud) obj;
		if (nrosolicitud == null) {
			if (other.nrosolicitud != null)
				return false;
		} else if (!nrosolicitud.equals(other.nrosolicitud))
			return false;
		return true;
	}

}
