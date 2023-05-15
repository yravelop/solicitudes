package com.example.solicitudes.negocio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.appframework.dto.CodCausalBeneficioDTO;
import com.example.solicitudes.modelo.CodCausalBeneficio;
import com.example.solicitudes.repositorio.CodCausalBeneficioRepo;

@Service

public class CodCausalBeneficioImpl implements CodCausalBeneficioBL {

	@Autowired
	private CodCausalBeneficioRepo repo;

	@Override
	public CodCausalBeneficioDTO addcodCausal(CodCausalBeneficioDTO dto) {
		CodCausalBeneficio causalBeneficio = new CodCausalBeneficio(null, null, dto.getCodCausalBeneficio(),
				dto.getDescripcion());
		repo.save(causalBeneficio);

		return dto;
	}

}
