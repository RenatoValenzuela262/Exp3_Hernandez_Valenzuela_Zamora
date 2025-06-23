package com.proyecto.springboot.backend.springboot_backend.services;

import java.util.List;
import java.util.Optional;

import com.proyecto.springboot.backend.springboot_backend.entities.Usuario;

public interface UsuarioService {

    List<Usuario> findByAll();

    Optional<Usuario> findByRut(Long rut);

    Usuario save(Usuario usuarioExistente);

    Optional<Usuario> delete(Usuario unUsuario);
}
