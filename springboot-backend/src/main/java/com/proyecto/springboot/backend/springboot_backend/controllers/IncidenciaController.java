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

import com.proyecto.springboot.backend.springboot_backend.entities.Incidencia;
import com.proyecto.springboot.backend.springboot_backend.services.IncidenciaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
@Tag(name = "Incidencias", description = "Operaciones relacionadas con Incidencias")
@RestController
@RequestMapping("api/incidencia")

public class IncidenciaController {

    @Autowired
    private IncidenciaService service;

    @Operation(summary = "Obtener lista de incidencias", description = "Devuelve todas las incidencias disponibles")
    @ApiResponse(responseCode = "200", description = "Lista de incidencias retornada correctamente",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Incidencia.class)))
    @GetMapping
    public List<Incidencia> List(){
        return service.findByAll();
    }

    @Operation(summary = "Obtener Incidencia por ID", description = "Obtiene el detalle de una incidencia especifica")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Incidencia encontrada",
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Incidencia.class))),
        @ApiResponse(responseCode = "404", description = "Incidencia no encontrada")
    })

    @GetMapping("/{id}")
    public ResponseEntity<?> verIncidencia(@PathVariable Long id){
        Optional<Incidencia> incidenciaOptional = service.findById(id);
        if (incidenciaOptional.isPresent()){
            return ResponseEntity.ok(incidenciaOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Crear una nueva incidencia", description = "Crea una incidencia con los datos proporcionados")
    @ApiResponse(responseCode = "201", description = "Incidencia creada correctamente",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = Incidencia.class)))
    @PostMapping
    public ResponseEntity<Incidencia> registrar(@RequestBody Incidencia unaIncidencia){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(unaIncidencia));
    }


    @Operation(summary = "Actualizar incidencia", description = "Actualiza la información de una incidencia existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Incidencia actualizada correctamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Incidencia.class))),
        @ApiResponse(responseCode = "404", description = "Incidencia no encontrado")
    })
    @PutMapping
    public ResponseEntity<?> modificar(@PathVariable Long id, @RequestBody Incidencia unaIncidencia){
        Optional<Incidencia> incidenciaOptional = service.findById(id);
        if (incidenciaOptional.isPresent()){
            Incidencia incidenciaexistente = incidenciaOptional.get();
            incidenciaexistente.setDescripcion(unaIncidencia.getDescripcion());
            incidenciaexistente.setEstado(unaIncidencia.getEstado());
            incidenciaexistente.setPrioridad(unaIncidencia.getPrioridad());
            Incidencia incidenciamodificada = service.save(incidenciaexistente);
            return ResponseEntity.ok(incidenciamodificada);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Eliminar incidencia", description = "Elimina una incidencia por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Incidencia eliminada correctamente"),
        @ApiResponse(responseCode = "404", description = "Incidencia no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        Incidencia unaIncidencia = new Incidencia();
        unaIncidencia.setId(id);
        Optional<Incidencia> incidenciaOptional = service.delete(unaIncidencia);
        if (incidenciaOptional.isPresent()){
            return ResponseEntity.ok(incidenciaOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();

    }

}