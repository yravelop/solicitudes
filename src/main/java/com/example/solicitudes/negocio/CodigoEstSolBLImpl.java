package com.example.solicitudes.negocio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.appframework.dto.CodigoEstSolDTO;
import com.example.solicitudes.modelo.CodigoEstSol;
import com.example.solicitudes.repositorio.CodigoEstSolRepo;

@Service
public class CodigoEstSolBLImpl implements CodigoEstSolBL {
	@Autowired
	CodigoEstSolRepo codigoEstSolRepo;

	@Override
	public CodigoEstSolDTO addcodigo(CodigoEstSolDTO dto) {
		CodigoEstSol codigoEst = new CodigoEstSol(null, null, dto.getCodigo(), dto.getDescripcion(),
				dto.getPermiteCambio());
		codigoEstSolRepo.save(codigoEst);
		return dto;
	}

	@Override
	public List<CodigoEstSolDTO> listar() {
		List<CodigoEstSol> listaCod = codigoEstSolRepo.findAll();
		List<CodigoEstSolDTO> listaDto = new ArrayList<>();
		for (CodigoEstSol codigo : listaCod) {
			CodigoEstSolDTO codigoDto = new CodigoEstSolDTO();
			codigoDto.setCodigo(codigo.getId());
			codigoDto.setDescripcion(codigo.getDescripcion());
			codigoDto.setPermiteCambio(codigo.getPermiteCambio());
			listaDto.add(codigoDto);
		}

		return listaDto;
	}

	@Override
	public CodigoEstSolDTO actualizar(CodigoEstSolDTO dto, Long id) throws Exception {
		Optional<CodigoEstSol> codigoEst = codigoEstSolRepo.findById(id);
		if (!codigoEst.isPresent()) {
			throw new Exception("El Codigo no existe");

		}
		CodigoEstSol nuevoCod = new CodigoEstSol();
		nuevoCod.setDescripcion(dto.getDescripcion());
		nuevoCod.setId(dto.getCodigo());
		nuevoCod.setPermiteCambio(dto.getPermiteCambio());
		nuevoCod = codigoEstSolRepo.save(nuevoCod);
		return dto;
	}

}
