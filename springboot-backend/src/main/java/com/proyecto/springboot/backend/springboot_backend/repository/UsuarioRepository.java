package com.proyecto.springboot.backend.springboot_backend.repository;

import org.springframework.data.repository.CrudRepository;

import com.proyecto.springboot.backend.springboot_backend.entities.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long>{

}
