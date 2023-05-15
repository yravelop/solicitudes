package com.example.solicitudes.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.solicitudes.modelo.RolesPersSolicitud;
import com.example.solicitudes.modelo.RolesPersSolicitudPK;

@Repository
public interface RolesPersSolicitudRepo extends JpaRepository<RolesPersSolicitud, RolesPersSolicitudPK> {
	List<RolesPersSolicitud> findByPkIdenficador(Long identificador);

	List<RolesPersSolicitud> findByPkIdenficadorAndPkCodRolPersona(Long identificador, Long codRolPersona);

}
