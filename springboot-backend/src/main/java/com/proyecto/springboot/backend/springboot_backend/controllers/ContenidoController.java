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

import com.proyecto.springboot.backend.springboot_backend.entities.Contenido;
import com.proyecto.springboot.backend.springboot_backend.services.ContenidoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;



@Tag(name = "Contenido", description = "Operaciones relacionadas con Contenido")
@RestController
@RequestMapping("api/contenido")
public class ContenidoController {

    @Autowired
    private ContenidoService service;

    @Operation(summary = "Obtener lista de contenido", description = "Devuelve todos los contenidos disponibles")
    @ApiResponse(responseCode = "200", description = "Lista de contenidos retornada correctamente",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Contenido.class)))
    @GetMapping
    public List<Contenido> List(){
        return service.findByAll();
    }
    @Operation(summary = "Obtener contenido por ID", description = "Obtiene el detalle de un contenido especifico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Contenido encontrado",
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Contenido.class))),
        @ApiResponse(responseCode = "404", description = "Contenido no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> verContenido(@PathVariable Long id){
        Optional<Contenido> contenidoOptional = service.findById(id);
        if (contenidoOptional.isPresent()){
            return ResponseEntity.ok(contenidoOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
    @Operation(summary = "Crear un nuevo contenido", description = "Crea un contenido con los datos proporcionados")
    @ApiResponse(responseCode = "201", description = "Contenido creado correctamente",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = Contenido.class)))
    @PostMapping
    public ResponseEntity<Contenido> crear(@RequestBody Contenido unContenido){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(unContenido));
    }

    @Operation(summary = "Actualizar contenido", description = "Actualiza la información de un contenido existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Contenido actualizado correctamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Contenido.class))),
        @ApiResponse(responseCode = "404", description = "Contenido no encontrado")
    })
    @PutMapping
    public ResponseEntity<?> modificar(@PathVariable Long id, @RequestBody Contenido unContenido){
        Optional<Contenido> contenidoOptional = service.findById(id);
        if (contenidoOptional.isPresent()){
            Contenido contenidoexistente = contenidoOptional.get();
            contenidoexistente.setNombre(unContenido.getNombre());
            contenidoexistente.setTipo(unContenido.getTipo());
            Contenido contenidomodificado = service.save(contenidoexistente);
            return ResponseEntity.ok(contenidomodificado);
        }
        return ResponseEntity.notFound().build();

    }
    @Operation(summary = "Eliminar contenido", description = "Elimina un contenido por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Contenido eliminado correctamente"),
        @ApiResponse(responseCode = "404", description = "Contenido no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        Contenido unContenido = new Contenido();
        unContenido.setId(id);
        Optional<Contenido> contenidoOptional = service.delete(unContenido);
        if(contenidoOptional.isPresent()){
            return ResponseEntity.ok(contenidoOptional.orElseThrow());
            
        }
        return ResponseEntity.notFound().build();
    }


}
