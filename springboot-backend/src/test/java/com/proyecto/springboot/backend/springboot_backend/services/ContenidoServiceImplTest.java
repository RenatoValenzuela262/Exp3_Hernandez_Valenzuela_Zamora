package com.proyecto.springboot.backend.springboot_backend.services;

import static org.junit.jupiter.api.Assertions.* ;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.proyecto.springboot.backend.springboot_backend.entities.Contenido;
import com.proyecto.springboot.backend.springboot_backend.repository.ContenidoRepository;


public class ContenidoServiceImplTest {

    @InjectMocks
    private ContenidoServiceImpl service;

    @Mock
    private ContenidoRepository repository;

    List<Contenido> list = new ArrayList<Contenido>();


    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);

        this.chargeContenido();
    }

    @Test
    public void findByAllTest(){

        when(repository.findAll()).thenReturn(list);

        List<Contenido> response = service.findByAll();

        assertEquals(3, response.size());
        
        verify(repository, times(1)).findAll();
    }

    public void chargeContenido(){
        Contenido cont1 = new Contenido(Long.valueOf(1), "Ecuaciones", "Materia");
        Contenido cont2 = new Contenido(Long.valueOf(2), "Sustantivos Propios", "Materia");
        Contenido cont3 = new Contenido(Long.valueOf(3), "Verbs", "Materia");

        list.add(cont1);
        list.add(cont2);
        list.add(cont3);

    }





}
