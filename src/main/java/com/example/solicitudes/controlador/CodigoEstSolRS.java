package com.example.solicitudes.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.appframework.dto.CodigoEstSolDTO;
import com.example.solicitudes.negocio.CodigoEstSolBL;

@RestController
@RequestMapping("/estado")
public class CodigoEstSolRS {
	@Autowired
	CodigoEstSolBL bl;

	@PostMapping()
	public CodigoEstSolDTO addcodigo(@RequestBody CodigoEstSolDTO dto) {
		return bl.addcodigo(dto);

	}

	@GetMapping()
	public List<CodigoEstSolDTO> listar() {
		return bl.listar();

	}

	@PutMapping("/actualizar/{id}")
	public CodigoEstSolDTO actualizar(@RequestBody CodigoEstSolDTO dto, Long id) throws Exception {
		return bl.actualizar(dto, id);
	}

}
