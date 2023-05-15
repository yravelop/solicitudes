package com.example.solicitudes.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.appframework.dto.CodCausalBeneficioDTO;
import com.example.solicitudes.negocio.CodCausalBeneficioBL;

@RestController
@RequestMapping("/codigoCausal")
public class CodCausalBeneficioRS {
	
	@Autowired
	private CodCausalBeneficioBL bl;
	
	@PostMapping()
	public CodCausalBeneficioDTO addcodCausal(@RequestBody CodCausalBeneficioDTO dto) {
		return bl.addcodCausal(dto);

	}

}
