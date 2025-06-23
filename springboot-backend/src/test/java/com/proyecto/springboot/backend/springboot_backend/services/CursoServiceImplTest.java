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

import com.proyecto.springboot.backend.springboot_backend.entities.Curso;
import com.proyecto.springboot.backend.springboot_backend.repository.CursoRepository;



public class CursoServiceImplTest {
    
    @InjectMocks
    private CursoServiceImpl service;

    @Mock
    private CursoRepository repository;

    List<Curso> list = new ArrayList<Curso>();

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);

        this.chargeCurso();
    }

    @Test
    public void findByAllTest(){
        when(repository.findAll()).thenReturn(list);

        List<Curso> response = service.findByAll();

        assertEquals( 3, response.size());

        verify(repository, times(1)).findAll();
    }
    
    public void chargeCurso(){
        Curso prod1 = new Curso(Long.valueOf(1),"Matematicas","Nivelacion" ,true);
        Curso prod2 = new Curso(Long.valueOf(2),"Lenguaje","Nivelacion" ,true);
        Curso prod3 = new Curso(Long.valueOf(3),"Ingles","Nivelacion" ,true);

        list.add(prod1);
        list.add(prod2);
        list.add(prod3);
    }


}
