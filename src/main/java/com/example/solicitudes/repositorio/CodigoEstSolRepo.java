package com.example.solicitudes.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.solicitudes.modelo.CodigoEstSol;

@Repository
public interface CodigoEstSolRepo extends JpaRepository<CodigoEstSol, Long> {

}
