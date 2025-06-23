package com.proyecto.springboot.backend.springboot_backend.services;

import static org.junit.jupiter.api.Assertions.*;
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

import com.proyecto.springboot.backend.springboot_backend.entities.Incidencia;
import com.proyecto.springboot.backend.springboot_backend.repository.IncidenciaRepository;


public class IncidenciaServiceImplTest {

    @InjectMocks
    private IncidenciaServiceImpl service;

    @Mock
    private IncidenciaRepository repository;

    List<Incidencia> list = new ArrayList<Incidencia>();

    @BeforeEach 
    public void init(){
        MockitoAnnotations.openMocks(this);

        this.chargeIncidencia();
    }

    @Test
    public void findByAllTest(){
        when(repository.findAll()).thenReturn(list);

        List<Incidencia> response = service.findByAll();

        assertEquals(4, response.size());
        verify(repository, times(1)).findAll();
    }

    public void chargeIncidencia(){
        Incidencia inci1 = new Incidencia(Long.valueOf(1), "Primera prueba de Incidencias", "Inactivo", "Alta" );
        Incidencia inci2 = new Incidencia(Long.valueOf(2), "Segunda prueba de Incidencias", "Inactivo", "Baja" );
        Incidencia inci3 = new Incidencia(Long.valueOf(3), "Probando errores", "Activo", "Media" );
        Incidencia inci4 = new Incidencia(Long.valueOf(4), "Probando soluciones", "Inactivo", "Alta" );

        list.add(inci1);
        list.add(inci2);
        list.add(inci3);
        list.add(inci4);
    }
}
