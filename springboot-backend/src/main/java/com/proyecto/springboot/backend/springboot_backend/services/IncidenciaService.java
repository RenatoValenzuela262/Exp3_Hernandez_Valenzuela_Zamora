package com.proyecto.springboot.backend.springboot_backend.services;

import java.util.List;
import java.util.Optional;

import com.proyecto.springboot.backend.springboot_backend.entities.Incidencia;


public interface IncidenciaService {

    List<Incidencia> findByAll();

    Optional<Incidencia> findById(Long id);

    Optional<Incidencia> delete(Incidencia unaIncidencia);

    Incidencia save(Incidencia incidenciaexistente);

    

}
