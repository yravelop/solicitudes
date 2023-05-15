package com.example.solicitudes.negocio;

import java.util.List;

import com.example.appframework.dto.CodigoEstSolDTO;

public interface CodigoEstSolBL {

	CodigoEstSolDTO addcodigo(CodigoEstSolDTO dto);

	List<CodigoEstSolDTO> listar();

	CodigoEstSolDTO actualizar(CodigoEstSolDTO dto, Long id) throws Exception;

	

	

}
