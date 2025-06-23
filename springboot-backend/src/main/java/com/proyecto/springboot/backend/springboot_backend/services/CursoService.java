package com.proyecto.springboot.backend.springboot_backend.services;

import java.util.List;
import java.util.Optional;

import com.proyecto.springboot.backend.springboot_backend.entities.Curso;

public interface CursoService {

    List<Curso> findByAll();

    Optional<Curso> findById(Long id);

    Curso save(Curso cursoexistente);

    Optional<Curso> delete(Curso unCurso);

}
