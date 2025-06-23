package com.proyecto.springboot.backend.springboot_backend.services;

import java.util.List;
import java.util.Optional;

import com.proyecto.springboot.backend.springboot_backend.entities.Cliente;


public interface ClienteService {

    List<Cliente> findByAll();

    Optional<Cliente> findById(Long id);

    Cliente save(Cliente unCliente);

    Optional<Cliente> delete(Cliente unCliente);

}
