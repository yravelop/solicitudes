package com.example.solicitudes.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.solicitudes.modelo.Solicitud;

@Repository
public interface SolicitudRepo extends JpaRepository<Solicitud, Long> {

}
