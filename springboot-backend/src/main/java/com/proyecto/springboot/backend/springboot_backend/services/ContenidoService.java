package com.proyecto.springboot.backend.springboot_backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.proyecto.springboot.backend.springboot_backend.entities.Contenido;

@Service
public interface ContenidoService {

    List<Contenido> findByAll();

    Optional<Contenido> findById(Long id);

    Contenido save(Contenido contenidoexistente);

    Optional<Contenido> delete(Contenido unContenido);

}
