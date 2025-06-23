package com.proyecto.springboot.backend.springboot_backend.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

import com.proyecto.springboot.backend.springboot_backend.entities.Cliente;
import com.proyecto.springboot.backend.springboot_backend.repository.ClienteRepository;

public class ClienteServiceImplTest {

    @InjectMocks
    private ClienteServiceImpl service;

    @Mock
    private ClienteRepository repository;

    List<Cliente> list = new ArrayList<Cliente>();

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);

        this.chargeCliente();
    }

    @Test
    public void findByAllTest(){
        when(repository.findAll()).thenReturn(list);

        List<Cliente> response = service.findByAll();
        assertEquals(4, response.size());
        verify(repository, times(1)).findAll();
    }

    public void chargeCliente(){
        Cliente cli1 = new Cliente(Long.valueOf(1), "Diego", "di.zamorag@duocuc.cl", "Chiringuito Chatarra", "Visa" );
        Cliente cli2 = new Cliente(Long.valueOf(2), "Maximiliano", "mac@duocuc.cl", "Pisos picados", "Visa" );
        Cliente cli3 = new Cliente(Long.valueOf(3), "Renato", "ren@duocuc.cl", "Parque placentero", "Masterdcard" );
        Cliente cli4 = new Cliente(Long.valueOf(4), "Anakin", "darthvader@duocuc.cl", "Señorio de la sal", "Centurion" );

        list.add(cli1);
        list.add(cli2);
        list.add(cli3);
        list.add(cli4);
    }
}

