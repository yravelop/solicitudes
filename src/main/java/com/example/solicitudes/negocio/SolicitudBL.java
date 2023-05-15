package com.example.solicitudes.negocio;

import java.util.List;

import com.example.appframework.dto.EstadoSolicitudDTO;
import com.example.appframework.dto.SolicitudDTO;

public interface SolicitudBL {

	SolicitudDTO addSolicitud(SolicitudDTO solicitudDTO) throws Exception;

	List<SolicitudDTO> listarSolicitud();

	SolicitudDTO actualizarSolicitud(SolicitudDTO solicitudDTO, Long nuSolicitud) throws Exception;

	void eliminarSolicitud(Long nuSolicitud) throws Exception;

	SolicitudDTO actualizarEstadoSolicitud(Long numeroSolicitud, Long estado, EstadoSolicitudDTO estadoSolicitudDTO)
			throws Exception;

	List<SolicitudDTO> obtenerSolicitudPersona(Long persIdentificador, Long codrolpersona) throws Exception;

	List<SolicitudDTO> obtenerSolicitudPorPersona(Long persIdentificador, Long codrolpersona) throws Exception;

	SolicitudDTO obtenerSolicitud(Long numeroSolicitud) throws Exception;

}
