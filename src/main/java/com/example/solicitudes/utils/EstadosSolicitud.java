package com.example.solicitudes.utils;

public enum EstadosSolicitud {

	INICIADA(1l), DENEGADA(2l), PENDIENTE(3l), OTORGADA(4l), SUSPENDIDA(5l);

	private Long codigo;

	private EstadosSolicitud(Long codigo) {
		this.codigo = codigo;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

}
