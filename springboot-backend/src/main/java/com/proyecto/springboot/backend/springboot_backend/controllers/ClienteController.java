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

import com.proyecto.springboot.backend.springboot_backend.entities.Cliente;
import com.proyecto.springboot.backend.springboot_backend.services.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Clientes", description = "Operaciones relacionadas con Clientes")
@RestController
@RequestMapping("api/cliente")

public class ClienteController {

    @Autowired
    private ClienteService service;

    @Operation(summary = "Obtener lista de clientes", description = "Devuelve todos los clientes disponibles")
    @ApiResponse(responseCode = "200", description = "Lista de clientes retornada correctamente",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Cliente.class)))
    @GetMapping
    public List<Cliente> List(){
        return service.findByAll();
    }

    @Operation(summary = "Obtener cliente por ID", description = "Obtiene el detalle de un cliente especifico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cliente encontrado",
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))),
        @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })

    @GetMapping("/{id}")
    public ResponseEntity<?> verCliente(@PathVariable Long id){
        Optional<Cliente> clienteOptional = service.findById(id);
        if (clienteOptional.isPresent()){
            return ResponseEntity.ok(clienteOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Crear un nuevo cliente", description = "Crea un cliente con los datos proporcionados")
    @ApiResponse(responseCode = "201", description = "Cliente creado correctamente",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class)))
    @PostMapping
    public ResponseEntity<Cliente> registrar(@RequestBody Cliente unCliente){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(unCliente));
    }

    @Operation(summary = "Actualizar cliente", description = "Actualiza la información de un cliente existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cliente actualizado correctamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))),
        @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    @PutMapping
    public ResponseEntity<?> modificar(@PathVariable Long id, @RequestBody Cliente unCliente){
        Optional<Cliente> clienteOptional = service.findById(id);
        if (clienteOptional.isPresent()){
            Cliente clienteexistente = clienteOptional.get();
            clienteexistente.setNombre(unCliente.getNombre());
            clienteexistente.setCorreo(unCliente.getCorreo());
            clienteexistente.setDireccion(unCliente.getDireccion());
            clienteexistente.setMetodoPago(unCliente.getMetodoPago());
            Cliente clientemodificado = service.save(clienteexistente);
            return ResponseEntity.ok(clientemodificado);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Eliminar cliente", description = "Elimina un cliente por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Cliente eliminado correctamente"),
        @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        Cliente unCliente = new Cliente();
        unCliente.setId(id);
        Optional<Cliente> clienteOptional = service.delete(unCliente);
        if (clienteOptional.isPresent()){
            return ResponseEntity.ok(clienteOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }



}
