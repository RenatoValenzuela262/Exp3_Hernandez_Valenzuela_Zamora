package com.proyecto.springboot.backend.springboot_backend.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.proyecto.springboot.backend.springboot_backend.entities.Usuario;
import com.proyecto.springboot.backend.springboot_backend.repository.UsuarioRepository;

public class UsuarioServiceImplTest {

    @InjectMocks
    private UsuarioServiceImpl service;

    @Mock
    private UsuarioRepository repository;

    List<Usuario> list = new ArrayList<Usuario>();

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);

        this.chargeUsuario();
    }

    @Test
    public void findByAllTest(){
        when(repository.findAll()).thenReturn(list);

        List<Usuario> response = service.findByAll();

        assertEquals(3, response.size());

        verify(repository, times(1)).findAll();
    }

    public void chargeUsuario(){
        Usuario usuario1 = new Usuario(Long.valueOf(203003009),"Usuario uno","usuariouno@duocuc.cl","contraUsuariouno");
        Usuario usuario2 = new Usuario(Long.valueOf(204004008),"Usuario dos","usuariodos@duocuc.cl","contraUsuariodos");
        Usuario usuario3 = new Usuario(Long.valueOf(205005007),"Usuario tres","usuariotres@duocuc.cl","contraUsuariotres");

        list.add(usuario1);
        list.add(usuario2);
        list.add(usuario3);
    }
}
