package com.example.solicitudes.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.appframework.dto.EstadoSolicitudDTO;
import com.example.appframework.dto.SolicitudDTO;
import com.example.solicitudes.negocio.SolicitudBL;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/solicitud")
public class SolicitudRS {

	@Autowired
	private SolicitudBL solicitudBL;

	@PostMapping()
	public SolicitudDTO addSolicitud(@RequestBody SolicitudDTO solicitudDTO) throws Exception {
		return solicitudBL.addSolicitud(solicitudDTO);
	}

	@GetMapping
	public List<SolicitudDTO> listarSolicitud() {
		return solicitudBL.listarSolicitud();
	}

	@PutMapping("/{numeroSolicitud}/actualizar")
	public SolicitudDTO actualizarSolicitud(@RequestBody SolicitudDTO solicitudDTO, @PathVariable Long numeroSolicitud)
			throws Exception {
		return solicitudBL.actualizarSolicitud(solicitudDTO, numeroSolicitud);
	}

	@DeleteMapping("/eliminar/{NuSolicitud}")
	public void eliminarSolicitud(@PathVariable Long NuSolicitud) throws Exception {
		solicitudBL.eliminarSolicitud(NuSolicitud);

	}

	@PutMapping("/{numeroSolicitud}/estado/{estado}")
	public SolicitudDTO actualizarEstadoSolicitud(@PathVariable Long numeroSolicitud, @PathVariable Long estado,
			@RequestBody EstadoSolicitudDTO estadoSolicitudDTO) throws Exception {
		return solicitudBL.actualizarEstadoSolicitud(numeroSolicitud, estado, estadoSolicitudDTO);
	}

	@GetMapping("/persona/{persIdentificador}")
	public List<SolicitudDTO> obtenerSolicitudPersona(@PathVariable Long persIdentificador,
			@RequestParam(name = "codrolpersona", required = false) Long codrolpersona) throws Exception {
		return solicitudBL.obtenerSolicitudPorPersona(persIdentificador, codrolpersona);

	}

	@GetMapping("/{numeroSolicitud}/obtener")
	public SolicitudDTO obtenerSolicitud(@PathVariable Long numeroSolicitud) throws Exception {
		return solicitudBL.obtenerSolicitud(numeroSolicitud);
	}

}
