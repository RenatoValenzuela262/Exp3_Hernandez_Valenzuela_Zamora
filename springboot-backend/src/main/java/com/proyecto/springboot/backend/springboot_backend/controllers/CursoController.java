package com.proyecto.springboot.backend.springboot_backend.controllers;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.springboot.backend.springboot_backend.entities.Curso;
import com.proyecto.springboot.backend.springboot_backend.services.CursoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "Cursos", description = "Operaciones relacionadas con Cursos")
@RestController
@RequestMapping("api/cursos")
public class CursoController {

    @Autowired
    private CursoService service;

    @Operation(summary = "Obtener lista de cursos", description = "Devuelve todos los cursos disponibles")
    @ApiResponse(responseCode = "200", description = "Lista de cursos retornada correctamente",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Curso.class)))
    @GetMapping
    public List<Curso> List(){
        return service.findByAll();
    }
    @Operation(summary = "Obtener curso por ID", description = "Obtiene el detalle de un curso especifico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Curso encontrado",
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Curso.class))),
        @ApiResponse(responseCode = "404", description = "Curso no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> verCurso(@PathVariable Long id){
        Optional<Curso> cursoOptional = service.findById(id);
        if (cursoOptional.isPresent()){
            return ResponseEntity.ok(cursoOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }



    @Operation(summary = "Crear un nuevo curso", description = "Crea un curso con los datos proporcionados")
    @ApiResponse(responseCode = "201", description = "Curso creado correctamente",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = Curso.class)))
    @PostMapping
    public ResponseEntity<Curso> crear(@RequestBody Curso unCurso){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(unCurso));
    }

    @Operation(summary = "Actualizar curso", description = "Actualiza la información de un curso existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Curso actualizado correctamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Curso.class))),
        @ApiResponse(responseCode = "404", description = "Curso no encontrado")
    })
    @PutMapping
    public ResponseEntity<?> modificar(@PathVariable Long id, @RequestBody Curso unCurso){
        Optional<Curso> cursoOptional = service.findById(id);
        if (cursoOptional.isPresent()){
            Curso cursoexistente = cursoOptional.get();
            cursoexistente.setTitulo(unCurso.getTitulo());
            cursoexistente.setDescripcion(unCurso.getDescripcion());
            cursoexistente.setPublicado(unCurso.getPublicado());
            Curso cursomodificado = service.save(cursoexistente);
            return ResponseEntity.ok(cursomodificado);
        }
        return ResponseEntity.notFound().build();

    }

    @Operation(summary = "Eliminar curso", description = "Elimina un curso por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Curso eliminado correctamente"),
        @ApiResponse(responseCode = "404", description = "Curso no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        Curso unCurso = new Curso();
        unCurso.setId(id);
        Optional<Curso> cursoOptional = service.delete(unCurso);
        if(cursoOptional.isPresent()){
            return ResponseEntity.ok(cursoOptional.orElseThrow());
            
        }
        return ResponseEntity.notFound().build();
    }

}
